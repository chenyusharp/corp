package com.xiazhenyu.corp.orm.scripting.xmltag;

import java.util.Arrays;
import java.util.List;
import org.apache.ibatis.session.Configuration;

/**
 * Date: 2023/1/23
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class WhereSqlNode extends TrimSqlNode {


    private static List<String> prefixList = Arrays.asList("AND", "OR", "AND\n", "OR\n", "AND\r", "OR\r", "AND\t", "OR\t");


    public WhereSqlNode(Configuration configuration, SqlNode contents) {
        super(contents, "WHERE",null, prefixList, null, configuration);
    }
}