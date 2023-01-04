package com.xiazhenyu.corp.orm.plugins;

import java.util.List;
import java.util.Properties;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.RowBounds;

/**
 * Date: 2022/12/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface Dialect {


    void setProperties(Properties properties);


    boolean skip(MappedStatement ms, Object parameterObject, RowBounds rowBounds);


    boolean beforeCount(MappedStatement ms, Object parameterObject, RowBounds rowBounds);

    String getCountSql(MappedStatement ms, BoundSql boundSql, Object parameterObject, RowBounds rowBounds, CacheKey coutKey);


    boolean afterCount(long count, Object parameterObject, RowBounds rowBounds);


    Object processParameterObject(MappedStatement ms, Object parameterObject, BoundSql boundSql, CacheKey pageKey);

    boolean beforePage(MappedStatement ms, Object parameterObject, RowBounds rowBounds);


    String getPageSql(MappedStatement ms, BoundSql boundSql, Object parameterObject, RowBounds rowBounds, CacheKey pageKey);


    Object afterPage(List pageList, Object parameterObject, RowBounds rowBounds);

    void afterAll();



}
