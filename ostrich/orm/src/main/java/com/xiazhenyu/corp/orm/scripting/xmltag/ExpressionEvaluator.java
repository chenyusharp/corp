package com.xiazhenyu.corp.orm.scripting.xmltag;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.scripting.xmltags.OgnlCache;

/**
 * Date: 2023/1/23
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ExpressionEvaluator {


    public boolean evaluateBoolean(String expression, Object parameterObject) {

        final Object value = OgnlCache.getValue(expression, parameterObject);

        if (value instanceof Boolean) {
            return ((Boolean) value);
        }
        if (value instanceof Number) {
            return !new BigDecimal(String.valueOf(value)).equals(BigDecimal.ZERO);
        }
        return value != null;
    }


    public Iterable<?> evaluateIterable(String expression, Object parameterObject) {
        final Object value = OgnlCache.getValue(expression, parameterObject);
        if (value == null) {
            throw new BuilderException("The expression'" + expression + "' evaluate to a null value");
        }
        if (value instanceof Iterable) {
            return ((Iterable<?>) value);
        }
        if (value.getClass().isArray()) {
            final int size = Array.getLength(value);
            final ArrayList<Object> answer = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                final Object o = Array.get(value, i);
                answer.add(o);
            }
            return answer;
        }

        if (value instanceof Map) {
            return ((Map<?, ?>) value).entrySet();
        }

        throw new BuilderException(
                "Error evaluate expression '" + expression + "'. Return value(" + value + ") was not iterable.");

    }


}