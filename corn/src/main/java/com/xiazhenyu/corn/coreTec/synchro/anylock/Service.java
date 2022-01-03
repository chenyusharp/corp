package com.xiazhenyu.corn.coreTec.synchro.anylock;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Service {

    private String userName;
    private String password;
    private String anyString = new String();

    public void getUserNamePassword(String userName, String password) {
        try {

            synchronized (anyString) {
                System.out.println("thread name=" + Thread.currentThread().getName() + "enter sync block");
                this.userName = userName;
                Thread.sleep(1000);
                this.password = password;
                System.out.println("thread name=" + Thread.currentThread().getName() + "exit sync block");

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}