package com.xiazhenyu.broccoli.aop;

import java.lang.reflect.AccessibleObject;

/**
 * Date: 2022/10/29
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ReflectUtil {


    public static <T> T newInstanceIfPossible(Class<T> type) {

        return null;

    }


    public static <T> T newInstance(Class<T> clazz, Object... params) {
        return null;
    }


    public static <T extends AccessibleObject> T setAccessible(T accessibleObject) {
        if (null != accessibleObject && false == accessibleObject.isAccessible()) {
            accessibleObject.setAccessible(true);
        }
        return accessibleObject;
    }


}