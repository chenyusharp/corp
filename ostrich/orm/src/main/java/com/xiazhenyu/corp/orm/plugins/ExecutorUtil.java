package com.xiazhenyu.corp.orm.plugins;

import com.xiazhenyu.corp.orm.plugins.BoundSqlInterceptor.Type;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.builder.annotation.ProviderSqlSource;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * Date: 2022/12/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public abstract class ExecutorUtil {

    private static Field additionalParametersField;

    private static Field providerMethodArgumentNamesField;


    static {
        try {
            additionalParametersField = BoundSql.class.getDeclaredField("additionalParameters");
            additionalParametersField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        try {
            providerMethodArgumentNamesField = ProviderSqlSource.class.getDeclaredField("providerMethodArgumentNames");
            providerMethodArgumentNamesField.setAccessible(true);
        } catch (Exception e) {

        }
    }


    public static MappedStatement getExistedMappedStatement(Configuration configuration, String msId) {
        return configuration.getMappedStatement(msId, false);
    }


    public static Long executeManualCount(Executor executor, MappedStatement countMs, Object parameter, BoundSql boundSql,
            ResultHandler resultHandler) throws SQLException {
        CacheKey countKey = executor.createCacheKey(countMs, parameter, RowBounds.DEFAULT, boundSql);
        BoundSql countBoundSql = countMs.getBoundSql(parameter);
        final List<Object> countResultList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey,
                countBoundSql);
        if (countResultList == null || countResultList.isEmpty()) {
            return 0L;
        }
        return ((Number) countResultList.get(0)).longValue();
    }


    public static Long executeAutoCount(Dialect dialect, Executor executor, MappedStatement countMs, Object parameter,
            BoundSql boundSql,
            RowBounds rowBounds, ResultHandler resultHandler) throws SQLException {
        final Map<String, Object> additionalParameters = getAdditionalParameter(boundSql);
        final CacheKey countKey = executor.createCacheKey(countMs, parameter, RowBounds.DEFAULT, boundSql);
        final String countSql = dialect.getCountSql(countMs, boundSql, parameter, rowBounds, countKey);
        BoundSql countBoundSql = new BoundSql(countMs.getConfiguration(), countSql, boundSql.getParameterMappings(), parameter);
        for (String key : additionalParameters.keySet()) {
            countBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
        }
        if (dialect instanceof BoundSqlInterceptor.Chain) {
            countBoundSql = ((BoundSqlInterceptor.Chain) dialect).doBoundSql(Type.COUNT_SQL, countBoundSql, countKey);
        }
        final List<Object> objectList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey,
                countBoundSql);
        if (objectList == null || objectList.isEmpty()) {
            return 0L;
        }
        return ((Number) objectList.get(0)).longValue();
    }


    public static <E> List<E> pageQuery(Dialect dialect, Executor executor, MappedStatement ms, Object parameter,
            RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql, CacheKey cacheKey) throws SQLException {
        if (dialect.beforePage(ms, parameter, rowBounds)) {
            CacheKey pageKey = cacheKey;
            parameter = dialect.processParameterObject(ms, parameter, boundSql, pageKey);
            final String pageSql = dialect.getPageSql(ms, boundSql, parameter, rowBounds, pageKey);
            BoundSql pageBoundSql = new BoundSql(ms.getConfiguration(), pageSql, boundSql.getParameterMappings(), parameter);
            final Map<String, Object> additionalParameters = getAdditionalParameter(boundSql);
            for (String key : additionalParameters.keySet()) {
                pageBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
            }

            if (dialect instanceof BoundSqlInterceptor.Chain) {
                pageBoundSql = ((BoundSqlInterceptor.Chain) dialect).doBoundSql(Type.PAGE_SQL, pageBoundSql, pageKey);
            }
            return executor.query(ms, parameter, RowBounds.DEFAULT, resultHandler, pageKey, pageBoundSql);
        } else {
            return executor.query(ms, parameter, RowBounds.DEFAULT, resultHandler, cacheKey, boundSql);
        }
    }


    public static Map<String, Object> getAdditionalParameter(BoundSql boundSql) {
        try {
            return (Map<String, Object>) additionalParametersField.get(boundSql);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }


}