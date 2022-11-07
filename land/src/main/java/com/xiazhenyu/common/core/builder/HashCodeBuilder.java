package com.xiazhenyu.common.core.builder;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Date: 2022/11/6
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class HashCodeBuilder implements Builder<Integer> {


    private static final long serialVersionUID = 8610676998309847195L;

    private static final int DEFAULT_INITIAL_VALUE = 17;


    private static final int DEFAULT_MULTIPLIER_VALUE = 37;


    private static final ThreadLocal<Set<IDKey>> REGISTRY = new ThreadLocal<>();


    private static Set<IDKey> getRegistry() {
        return REGISTRY.get();
    }

    static void register(final Object value) {
        synchronized (HashCodeBuilder.class) {
            if (getRegistry() == null) {
                REGISTRY.set(new HashSet<>());
            }
        }
        getRegistry().add(new IDKey(value));

    }


    static void unregister(final Object value) {
        Set<IDKey> registry = getRegistry();
        if (registry != null) {
            registry.remove(new IDKey(value));
            synchronized (HashCodeBuilder.class) {
                registry = getRegistry();
                if (registry != null && registry.isEmpty()) {
                    REGISTRY.remove();
                }
            }
        }
    }


    private static boolean isRegistered(final Object value) {
        final Set<IDKey> registry = getRegistry();
        return registry != null && registry.contains(new IDKey(value));
    }


    private static void reflectionAppend(
            final Object object,
            final Class<?> clazz,
            final HashCodeBuilder builder,
            final boolean useTransients,
            final String[] excludeFields
    ) {
        if (isRegistered(object)) {
            return;
        }
        try {
            register(object);
            final Field[] fields = clazz.getFields();
            for (Field field : fields) {
                if (false == Arrays.stream(excludeFields).anyMatch(x -> x.equals(field.getName()))
                        && (field.getName().indexOf('$')) == -1
                        && (useTransients || !Modifier.isTransient(field.getModifiers()))
                        && (!Modifier.isStatic(field.getModifiers()))
                ) {
                    final Object fieldValue = field.get(object);

                }


            }


        } catch (final IllegalAccessException e) {

        }


    }


    private final int iConstant;

    private int iTotal;


    public HashCodeBuilder() {
        this.iConstant = 37;
        this.iTotal = 17;
    }

    public HashCodeBuilder append(final char value) {
        iTotal = iTotal * iConstant + value;
        return this;
    }

    public HashCodeBuilder append(final boolean value) {
        iTotal = iTotal * iConstant + (value ? 0 : 1);
        return this;
    }


    public HashCodeBuilder append(final boolean[] array) {
        if (array == null) {
            iTotal = iTotal * iConstant;
        } else {
            for (final boolean element : array) {
                append(element);
            }
        }
        return this;
    }


    public HashCodeBuilder append(final byte value) {
        iTotal = iTotal * iConstant + value;
        return this;
    }


    public HashCodeBuilder append(final byte[] array) {
        if (array == null) {
            iTotal = iTotal * iConstant;
        } else {
            for (final byte element : array) {
                append(element);
            }
        }
        return this;
    }


    public HashCodeBuilder append(final char[] array) {

        if (array == null) {
            iTotal = iTotal * iConstant;
        } else {
            for (final char element : array) {
                append(element);
            }
        }
        return this;
    }

    public HashCodeBuilder append(final double value) {
        return append(Double.doubleToLongBits(value));
    }

    public HashCodeBuilder append(final double[] array) {
        if (array == null) {
            iTotal = iTotal * iConstant;
        } else {
            for (final double element : array) {
                append(element);
            }
        }
        return this;
    }


    public HashCodeBuilder append(final float value) {
        iTotal = iTotal * iConstant + Float.floatToIntBits(value);
        return this;
    }





    @Override
    public Integer build() {
        return null;
    }
}