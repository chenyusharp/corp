package com.xiazhenyu.corp.orm.scripting.xmltag;

import java.util.List;

/**
 * Date: 2023/1/24
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MixedSqlNode implements SqlNode {

    private List<SqlNode> contents;

    public MixedSqlNode(List<SqlNode> contents) {
        this.contents = contents;
    }

    @Override
    public boolean apply(DynamicContext context) {
        for (SqlNode sqlNode : contents) {
            sqlNode.apply(context);
        }
        return true;
    }
}