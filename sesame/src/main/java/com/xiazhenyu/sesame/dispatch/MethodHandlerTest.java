package com.xiazhenyu.sesame.dispatch;

import static java.lang.invoke.MethodHandles.lookup;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * Date: 2022/1/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MethodHandlerTest {


    static class ClassA {

        public void println(String s) {
            System.out.println(s);
        }
    }


    public static void main(String[] args) throws Throwable {
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        getPrintlnMH(obj).invokeExact("xiazhenyu");

    }

    private static MethodHandle getPrintlnMH(Object reveiver) throws NoSuchMethodException, IllegalAccessException {
        MethodType mt = MethodType.methodType(void.class, String.class);
        return lookup().findVirtual(reveiver.getClass(), "println", mt).bindTo(reveiver);


    }


}