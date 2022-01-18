package com.xiazhenyu.sesame.dispatch;

/**
 * Date: 2022/1/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class DynamicDispatch {


    static abstract class Human {

        protected abstract void sayHello();
    }

    static class Man extends Human {

        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Woman extends Human {

        @Override
        protected void sayHello() {
            System.out.println("woman say Hello");
        }
    }


    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man=new Woman();
        man.sayHello();
    }


}