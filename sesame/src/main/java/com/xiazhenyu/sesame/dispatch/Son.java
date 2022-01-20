package com.xiazhenyu.sesame.dispatch;

import static java.lang.invoke.MethodHandles.lookup;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * Date: 2022/1/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Son extends Father {

    @Override
    void thinking() {

        try {
            MethodType mt = MethodType.methodType(void.class);
            Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            lookupImpl.setAccessible(true);
            MethodHandle mh = ((MethodHandles.Lookup) lookupImpl.get(null))
                    .findSpecial(GrandFather.class, "thinking", mt, getClass());
            mh.invoke(this);
        } catch (Throwable throwable) {

        }

    }


    public static void main(String[] args) {
        new Son().thinking();

    }
}



