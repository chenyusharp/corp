package com.xiazhenyu.common.core.builder;

import java.io.Serializable;

/**
 * Date: 2022/11/6
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class IDKey implements Serializable {

    private static final long serialVersionUID = 3852713791437970471L;


    private final Object value;
    private final int id;


    public IDKey(Object value) {
        this.value = value;
        this.id = System.identityHashCode(value);
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IDKey)) {
            return false;
        }
        final IDKey idKey = (IDKey) obj;
        if (id != idKey.id) {
            return false;
        }
        return value == idKey.value;
    }
}