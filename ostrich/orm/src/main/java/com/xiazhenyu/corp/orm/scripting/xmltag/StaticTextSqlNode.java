package com.xiazhenyu.corp.orm.scripting.xmltag;

/**
 * Date: 2023/1/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class StaticTextSqlNode implements SqlNode {

    private String text;

    public StaticTextSqlNode(String text) {
        this.text = text;
    }

    @Override
    public boolean apply(DynamicContext context) {
        context.appendSql(text);
        return true;
    }
}