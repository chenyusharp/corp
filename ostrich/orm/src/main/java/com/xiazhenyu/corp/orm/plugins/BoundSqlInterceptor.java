package com.xiazhenyu.corp.orm.plugins;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;

/**
 * Date: 2022/12/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface BoundSqlInterceptor {


    BoundSql boundSql(Type type, BoundSql boundSql, CacheKey cacheKey, Chain chain);


    enum Type {

        ORIGINAL,

        COUNT_SQL,

        PAGE_SQL

    }


    interface Chain {

        Chain DO_NOTHING = new Chain() {
            @Override
            public BoundSql doBoundSql(Type type, BoundSql boundSql, CacheKey cacheKey) {
                return boundSql;
            }
        };


        BoundSql doBoundSql(Type type, BoundSql boundSql, CacheKey cacheKey);
    }


}
