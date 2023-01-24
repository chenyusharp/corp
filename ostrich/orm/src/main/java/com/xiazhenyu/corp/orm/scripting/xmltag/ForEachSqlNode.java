package com.xiazhenyu.corp.orm.scripting.xmltag;

import java.util.Map;
import java.util.Map.Entry;
import org.apache.ibatis.parsing.GenericTokenParser;
import org.apache.ibatis.session.Configuration;

/**
 * Date: 2023/1/23
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ForEachSqlNode implements SqlNode {

    public static final String ITEM_PREFIX = "__frch_";
    private ExpressionEvaluator evaluator;
    private String collectionExpress;
    private SqlNode contents;
    private String open;
    private String close;
    private String separator;
    private String item;
    private String index;
    private Configuration configuration;

    private ForEachSqlNode(Configuration configuration, SqlNode contents, String collectionExpress, String index, String item,
            String open, String close, String separator) {
        this.evaluator = new ExpressionEvaluator();
        this.collectionExpress = collectionExpress;
        this.contents = contents;
        this.open = open;
        this.close = close;
        this.separator = separator;
        this.index = index;
        this.item = item;
        this.configuration = configuration;
    }


    @Override
    public boolean apply(DynamicContext context) {
        final Map<String, Object> bindings = context.getBindings();
        final Iterable<?> iterable = evaluator.evaluateIterable(collectionExpress, bindings);
        boolean first = true;
        applyOpen(context);
        int i = 0;
        for (Object o : iterable) {
            DynamicContext oldContext = context;
            if (first) {
                context = new PrefixedContent(context, "");
            } else {
                if (separator != null) {
                    context = new PrefixedContent(context, separator);
                } else {
                    context = new PrefixedContent(context, "");
                }
            }
            final int uniqueNumber = context.getUniqueNumber();
            if (o instanceof Map.Entry) {
                final Entry<?, ?> mapEntry = (Entry<?, ?>) o;
                applyIndex(context, mapEntry.getKey(), uniqueNumber);
                applyItem(context, mapEntry.getValue(), uniqueNumber);
            } else {
                applyIndex(context, i, uniqueNumber);
                applyItem(context, o, uniqueNumber);
            }
            contents.apply(new FilteredDynamicContent(configuration, context, index, item, uniqueNumber));
            if (first) {
                first = !((PrefixedContent) context).isPrefixedApplied();
            }
            context = oldContext;
            applyClose(context);
            return true;
        }
        return false;
    }

    private void applyItem(DynamicContext context, Object o, int i) {
        if (item != null) {
            context.bind(item, o);
            context.bind(itemizeItem(item, i), o);
        }
    }

    private void applyIndex(DynamicContext context, Object o, int i) {
        if (index != null) {
            context.bind(index, o);
            context.bind(itemizeItem(index, i), o);
        }
    }


    private static String itemizeItem(String item, int i) {
        return new StringBuilder(ITEM_PREFIX).append(item).append("_").append(i).toString();
    }


    private void applyOpen(DynamicContext context) {
        if (open != null) {
            context.appendSql(open);
        }
    }


    private void applyClose(DynamicContext context) {
        if (close != null) {
            context.appendSql(close);
        }
    }


    private class PrefixedContent extends DynamicContext {

        private DynamicContext delegate;
        private String prefix;
        private boolean prefixedApplied;

        public PrefixedContent(DynamicContext delegates, String prefix) {
            super(configuration, null);
            this.delegate = delegates;
            this.prefix = prefix;
            this.prefixedApplied = false;
        }


        public boolean isPrefixedApplied() {
            return prefixedApplied;
        }


        @Override
        public Map<String, Object> getBindings() {
            return delegate.getBindings();
        }

        @Override
        public void bind(String name, Object value) {
            delegate.bind(name, value);
        }

        @Override
        public void appendSql(String sql) {
            if (!prefixedApplied && sql != null && sql.trim().length() > 0) {
                delegate.appendSql(prefix);
                prefixedApplied = true;
            }
            delegate.appendSql(sql);
        }

        @Override
        public String getSql() {
            return delegate.getSql();
        }

        @Override
        public void setUniqueNumber(int uniqueNumber) {
            delegate.setUniqueNumber(uniqueNumber);
        }
    }


    private class FilteredDynamicContent extends DynamicContext {

        private DynamicContext delegate;
        private int index;
        private String itemIndex;
        private String item;


        public FilteredDynamicContent(Configuration configuration, DynamicContext deletgate, String itemIndex, String item,
                int i) {
            super(configuration, null);
            this.delegate = deletgate;
            this.index = i;
            this.itemIndex = itemIndex;
            this.item = item;
        }


        @Override
        public Map<String, Object> getBindings() {
            return delegate.getBindings();
        }

        @Override
        public void bind(String name, Object value) {
            delegate.bind(name, value);
        }

        @Override
        public void appendSql(String sql) {
            GenericTokenParser parser = new GenericTokenParser("#{", "}", content -> {
                String newContent = content.replaceFirst("^\\s*" + item + "(?![^.,:\\s])", itemizeItem(item, index));
                if (itemIndex != null && newContent.equals(content)) {
                    newContent = content.replaceFirst("^\\s*" + itemIndex + "(?![^.,:\\s])", itemizeItem(itemIndex, index));
                }
                return "#{" + newContent + "}";
            });
            delegate.appendSql(parser.parse(sql));
        }

        @Override
        public String getSql() {
            return delegate.getSql();
        }

        @Override
        public void setUniqueNumber(int uniqueNumber) {
            delegate.setUniqueNumber(uniqueNumber);
        }
    }
}