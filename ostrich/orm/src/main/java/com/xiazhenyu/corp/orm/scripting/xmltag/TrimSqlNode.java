package com.xiazhenyu.corp.orm.scripting.xmltag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.ibatis.session.Configuration;

/**
 * Date: 2023/1/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class TrimSqlNode implements SqlNode {


    private SqlNode contents;

    private String prefix;

    private String suffix;


    private List<String> prefixesToOverride;
    private List<String> suffixesToOverride;
    private Configuration configuration;


    public TrimSqlNode(Configuration configuration, SqlNode contents, String prefix, String prefixesToOverride, String suffix,
            String suffixesToOverride) {
        this(contents, prefix, suffix, parseOverrides(prefixesToOverride), parseOverrides(suffixesToOverride), configuration);
    }


    protected TrimSqlNode(SqlNode contents, String prefix, String suffix, List<String> prefixesToOverride,
            List<String> suffixesToOverride, Configuration configuration) {
        this.contents = contents;
        this.prefix = prefix;
        this.suffix = suffix;
        this.prefixesToOverride = prefixesToOverride;
        this.suffixesToOverride = suffixesToOverride;
        this.configuration = configuration;
    }


    private static List<String> parseOverrides(String overrides) {
        if (overrides != null) {
            final StringTokenizer tokenizer = new StringTokenizer(overrides, "|", false);
            return new ArrayList<String>() {
                private static final long serialVersionUID = -42593737048728135L;

                {
                    while (tokenizer.hasMoreTokens()) {
                        add(tokenizer.nextToken().toUpperCase(Locale.ENGLISH));
                    }
                }
            };
        }
        return Collections.emptyList();
    }


    @Override
    public boolean apply(DynamicContext context) {
        FilteredDynamicContext filteredDynamicContext = new FilteredDynamicContext(context);
        final boolean result = contents.apply(filteredDynamicContext);
        filteredDynamicContext.applyAll();
        return result;
    }


    public void applyAll() {

    }


    private class FilteredDynamicContext extends DynamicContext {

        private DynamicContext delegate;
        private boolean prefixApplied;
        private boolean suffixApplied;
        private StringBuilder sqlBuffer;

        public FilteredDynamicContext(DynamicContext delegate) {
            super(configuration, null);
            this.delegate = delegate;
            this.prefixApplied = false;
            this.suffixApplied = false;
            this.sqlBuffer = new StringBuilder();
        }


        public void applyAll() {
            sqlBuffer = new StringBuilder(sqlBuffer.toString().trim());
            final String trimmedUppercaseSql = sqlBuffer.toString().toUpperCase(Locale.ENGLISH);
            if (trimmedUppercaseSql.length() > 0) {
                applyPrefix(sqlBuffer, trimmedUppercaseSql);
                applySuffix(sqlBuffer, trimmedUppercaseSql);
            }
            delegate.appendSql(sqlBuffer.toString());
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
            sqlBuffer.append(sql);
        }

        @Override
        public String getSql() {
            return delegate.getSql();
        }

        @Override
        public void setUniqueNumber(int uniqueNumber) {
            delegate.setUniqueNumber(uniqueNumber);
        }

        private void applyPrefix(StringBuilder sql, String trimmedUppercaseSql) {
            if (!prefixApplied) {
                prefixApplied = true;
                if (prefixesToOverride != null) {
                    for (String toRemove : prefixesToOverride) {
                        if (trimmedUppercaseSql.startsWith(toRemove)) {
                            sql.delete(0, toRemove.trim().length());
                            break;
                        }
                    }
                }
                if (prefix != null) {
                    sql.insert(0, " ");
                    sql.insert(0, prefix);
                }
            }
        }


        private void applySuffix(StringBuilder sql, String trimmedUpcaseSql) {
            if (!suffixApplied) {
                suffixApplied = true;
                if (suffixesToOverride != null) {
                    for (String toRemove : suffixesToOverride) {
                        if (trimmedUpcaseSql.endsWith(toRemove) || trimmedUpcaseSql.endsWith(toRemove.trim())) {
                            final int start = sql.length() - toRemove.trim().length();
                            final int end = sql.length();
                            sql.delete(start, end);
                            break;
                        }
                    }
                }
                if (suffix != null) {
                    sql.append(" ");
                    sql.append(suffix);
                }
            }
        }


    }


    public static void main(String[] args) {
        StringBuilder text = new StringBuilder("love china");
        System.out.println(text.insert(0, "I").insert(1, " "));

    }


}