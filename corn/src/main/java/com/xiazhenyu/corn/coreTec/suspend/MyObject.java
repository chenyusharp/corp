package com.xiazhenyu.corn.coreTec.suspend;

/**
 * Date: 2022/1/2
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MyObject {

    private String username = "1";
    private String password = "1";

    public void setValue(String username, String password) {
        this.username = username;
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("thread is suspend");
            Thread.currentThread().suspend();
        }
        this.password = password;
    }

    public void printUserNameAndPassword() {
        System.out.println(username + " " + password);


    }

}