package com.xiazhenyu.sesame.classLoader;

/**
 * Date: 2022/1/8
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class NotInitialization {


    public static void main(String[] args) {
        //对于静态字段，只有直接定义这个字段的类才会被初始化。
        //通过子类引用父类的静态字段，不会导致子类的初始化
//        System.out.println(SubClass.value);

        //父类的初始化没有执行，触发了一个array数组的初始化。
        //通过数组定义来引用，不会触发此类的初始化。
//        SuperClass[] sca = new SuperClass[10];

        //常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类，因此不会触发定义常量类的初始化
        System.out.println(ConstantClass.HELLOWORLD);


    }

}