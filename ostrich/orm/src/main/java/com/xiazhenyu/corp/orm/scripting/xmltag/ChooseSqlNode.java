package com.xiazhenyu.corp.orm.scripting.xmltag;

import java.util.List;

/**
 * Date: 2023/1/24
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ChooseSqlNode implements SqlNode {

    private SqlNode defaultSqlNode;
    private List<SqlNode> ifSqlNodes;


    public ChooseSqlNode(SqlNode defaultSqlNode, List<SqlNode> ifSqlNodes) {
        this.defaultSqlNode = defaultSqlNode;
        this.ifSqlNodes = ifSqlNodes;
    }

    @Override
    public boolean apply(DynamicContext context) {
        for (SqlNode ifSqlNode : ifSqlNodes) {
            if (ifSqlNode.apply(context)) {
                return true;
            }
        }
        if (defaultSqlNode != null) {
            defaultSqlNode.apply(context);
            return true;
        }
        return false;
    }
}