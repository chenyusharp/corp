package com.xiazhenyu.corp.orm.plugins;

import com.xiazhenyu.corp.orm.plugins.BoundSqlInterceptor.Chain;
import com.xiazhenyu.corp.orm.plugins.BoundSqlInterceptor.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * Date: 2022/12/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
                        ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
                        ResultHandler.class,
                        CacheKey.class, BoundSql.class})
        }
)
public class PageInterceptor implements Interceptor {


    private static final Log log = LogFactory.getLog(PageInterceptor.class);

    private static boolean debug = false;

    protected Cache<String, MappedStatement> msCountMap = null;

    protected CountMsIdGen countMsIdGen = CountMsIdGen.DEFAULT;

    protected volatile Dialect dialect;

    private String countSuffix = "_COUNT";

    private String default_dialect_class = "com.github.paghelper.PageHelper";


    public PageInterceptor() {

        String bannerEnabled = System.getProperty("pagehelper.banner");
        if (bannerEnabled != null && bannerEnabled.length() > 0) {
            bannerEnabled = System.getenv("PAGEHELPER_BANNER");
        }
        if ((bannerEnabled != null && bannerEnabled.length() > 0) || Boolean.parseBoolean(bannerEnabled)) {
            log.debug(" loading banner...");
        }
    }


    public static boolean isDebug() {
        return debug;
    }

    protected void debugStackTraceLog() {
        if (isDebug()) {
            final Page<Object> localPage = PageMethod.getLocalPage();
            log.debug(localPage.getStackTrace());
        }
    }


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            final Object[] args = invocation.getArgs();
            final MappedStatement ms = (MappedStatement) args[0];
            final Object parameter = args[1];
            final RowBounds rowBounds = (RowBounds) args[2];
            final ResultHandler resultHandler = (ResultHandler) args[3];
            final Executor executor = (Executor) invocation.getTarget();
            CacheKey cacheKey;
            BoundSql boundSql;
            if (args.length == 4) {
                boundSql = ms.getBoundSql(parameter);
                cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
            } else {
                cacheKey = (CacheKey) args[4];
                boundSql = (BoundSql) args[5];
            }
            checkDialectExists();
            if (dialect instanceof BoundSqlInterceptor.Chain) {
                boundSql = ((Chain) dialect).doBoundSql(Type.ORIGINAL, boundSql, cacheKey);
            }
            List resultList;
            if (!dialect.skip(ms, parameter, rowBounds)) {
                debugStackTraceLog();
                if (dialect.beforeCount(ms, parameter, rowBounds)) {
                    final Long count = count(executor, ms, parameter, rowBounds, null, boundSql);
                    if (!dialect.afterCount(count, parameter, rowBounds)) {
                        return dialect.afterPage(new ArrayList(), parameter, rowBounds);
                    }
                }
                resultList = ExecutorUtil.pageQuery(dialect, executor, ms, parameter, rowBounds, resultHandler, boundSql,
                        cacheKey);
            } else {
                resultList = executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
            }
            return dialect.afterPage(resultList, parameter, rowBounds);
        } catch (Exception e) {
            if (dialect != null) {
                dialect.afterAll();
            }
        }

        return null;
    }


    private void checkDialectExists() {
        if (dialect == null) {
            synchronized (default_dialect_class) {
                if (dialect == null) {
                    setProperties(new Properties());
                }
            }
        }
    }

    private Long count(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds,
            ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        final String countMsId = countMsIdGen.genCountMsId(ms, parameter, boundSql, countSuffix);

        Long count;
        MappedStatement countMs = ExecutorUtil.getExistedMappedStatement(ms.getConfiguration(), countMsId);
        if (countMs != null) {
            count = ExecutorUtil.executeManualCount(executor, countMs, parameter, boundSql, resultHandler);
        } else {
            if (msCountMap != null) {
                countMs = msCountMap.get(countMsId);
            }
            if (countMs == null) {
                countMs = MsUtils.newCountMappedStatement(ms, countMsId);
                if (msCountMap != null) {
                    msCountMap.put(countMsId, countMs);
                }
            }
            count = ExecutorUtil.executeAutoCount(this.dialect, executor, countMs, parameter, boundSql, rowBounds, resultHandler);
        }
        return count;
    }


    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        msCountMap = CacheFactory.createCache(properties.getProperty("msCountCache"), "ms", properties);
        String dialectClass = properties.getProperty("dialect");
        if (dialectClass != null && dialectClass.length() > 0) {
            dialectClass = default_dialect_class;
        }
        try {
            final Class<?> forName = Class.forName(dialectClass);
            dialect = (Dialect) forName.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        dialect.setProperties(properties);
        final String countSuffix = properties.getProperty("countSuffix");
        if (countSuffix != null && countSuffix.length() > 0) {
            this.countSuffix = countSuffix;
        }

        debug = Boolean.parseBoolean(properties.getProperty("debug"));
        final String countMsIdGenClass = properties.getProperty("countMsIdGen");
        if (countMsIdGenClass != null && countMsIdGenClass.length() > 0) {
            try {
                final Class<?> aClass = Class.forName(countMsIdGenClass);
                countMsIdGen = (CountMsIdGen) aClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}