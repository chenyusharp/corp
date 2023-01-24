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
public class SetSqlNode extends TrimSqlNode {

    private static List<String> suffixList = Arrays.asList(",");

    protected SetSqlNode(SqlNode contents, Configuration configuration) {
        super(contents, "SET", null, null, suffixList, configuration);
    }
}