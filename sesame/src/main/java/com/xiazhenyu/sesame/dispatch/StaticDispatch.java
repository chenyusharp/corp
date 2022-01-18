package com.xiazhenyu.sesame.dispatch;

/**
 * Date: 2022/1/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class StaticDispatch {


    public static class Human {

    }

    public static class Man extends Human {

    }

    public static class Woman extends Human {

    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }


    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
    }


}