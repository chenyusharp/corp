package com.xiazhenyu.corn.coreTec.communication.wait;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Test {


    public static void main(String[] args) {
        MyList myList = new MyList();

        ThreadB threadB = new ThreadB(myList);
        threadB.setName("b");
        threadB.start();

        ThreadA threadA = new ThreadA(myList);
        threadA.setName("a");
        threadA.start();



    }

}