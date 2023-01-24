package com.xiazhenyu.corp.orm.scripting.xmltag;

import org.apache.ibatis.parsing.GenericTokenParser;
import org.apache.ibatis.parsing.TokenHandler;
import org.apache.ibatis.scripting.xmltags.OgnlCache;
import org.apache.ibatis.type.SimpleTypeRegistry;

/**
 * Date: 2023/1/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class TextSqlNode implements SqlNode {


    private String text;

    public TextSqlNode(String text) {
        this.text = text;
    }

    public boolean isDynamic() {
        DynamicCheckerTokenParser checker = new DynamicCheckerTokenParser();
        final GenericTokenParser parser = createParser(checker);
        return checker.isDynamic();
    }


    private GenericTokenParser createParser(TokenHandler handler) {
        return new GenericTokenParser("${", "}", handler);
    }


    @Override
    public boolean apply(DynamicContext context) {
        final GenericTokenParser parser = createParser(new BindingTokenParser(context));
        context.appendSql(parser.parse(text));
        return true;
    }

    private static class BindingTokenParser implements TokenHandler {

        private DynamicContext context;

        public BindingTokenParser(DynamicContext context) {
            this.context = context;
        }

        @Override
        public String handleToken(String content) {
            final Object parameter = context.getBindings().get("_parameter");
            if (parameter == null) {
                context.getBindings().put("value", null);
            } else if (SimpleTypeRegistry.isSimpleType(parameter.getClass())) {
                context.getBindings().put("value", parameter);
            }
            final Object value = OgnlCache.getValue(content, context);
            return value == null ? "" : String.valueOf(value);
        }
    }


    private static class DynamicCheckerTokenParser implements TokenHandler {

        private boolean isDynamic;

        public boolean isDynamic() {
            return isDynamic;
        }

        @Override
        public String handleToken(String content) {
            this.isDynamic = true;
            return null;
        }
    }


}