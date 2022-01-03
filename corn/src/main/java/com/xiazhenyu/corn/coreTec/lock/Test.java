package com.xiazhenyu.corn.coreTec.lock;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * Date: 2022/1/4
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Test {


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);

        System.out.println("begin=" + System.currentTimeMillis());
        //park中的第一个参数的含义：代表是否是绝对时间；
        //true：绝对时间，false：不是绝对时间
        //true的时候，第二个参数的单位是毫秒
        //false的时候，第二个参数的单位是纳秒
        //true的时候，意味着需要park到第二个参数制定的时间,如果第二个时间小于等于系统当前的时间的话，则相当于不进行park；
        //false的时候，意味着需要park的时常是有第二个参数控制的。小于等于0也意味着不进行park
        unsafe.park(false, -110);
        System.out.println("end=" + System.currentTimeMillis());
    }

}