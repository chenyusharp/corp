package com.xiazhenyu.corp.orm.scripting.xmltag;

/**
 * Date: 2023/1/24
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class IfSqlNode implements SqlNode {

    private ExpressionEvaluator evaluator;
    private String test;
    private SqlNode contents;


    public IfSqlNode(String test, SqlNode contents) {
        this.evaluator = new ExpressionEvaluator();
        this.test = test;
        this.contents = contents;
    }

    @Override
    public boolean apply(DynamicContext context) {
        if (evaluator.evaluateBoolean(test, context.getBindings())) {
            contents.apply(context);
            return true;
        }
        return false;
    }
}