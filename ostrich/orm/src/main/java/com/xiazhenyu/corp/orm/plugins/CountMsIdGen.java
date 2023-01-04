package com.xiazhenyu.corp.orm.plugins;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * Date: 2022/12/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface CountMsIdGen {


    CountMsIdGen DEFAULT = new CountMsIdGen() {
        @Override
        public String genCountMsId(MappedStatement ms, Object parameter, BoundSql boundSql, String countSuffix) {
            return ms.getId() + countSuffix;
        }
    };


    String genCountMsId(MappedStatement ms, Object parameter, BoundSql boundSql, String countSuffix);


}