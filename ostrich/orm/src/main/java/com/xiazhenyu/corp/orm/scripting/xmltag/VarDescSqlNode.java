package com.xiazhenyu.corp.orm.scripting.xmltag;

import org.apache.ibatis.scripting.xmltags.OgnlCache;

/**
 * Date: 2023/1/24
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class VarDescSqlNode implements SqlNode {


    private final String name;
    private final String expression;

    public VarDescSqlNode(String name, String expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public boolean apply(DynamicContext context) {

        final Object value = OgnlCache.getValue(expression, context.getBindings());
        context.bind(name, value);
        return true;
    }
}