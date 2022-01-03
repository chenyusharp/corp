package com.xiazhenyu.corn.coreTec.threadLocal;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ThreadLocalNotExtends {
    private ThreadLocalNotExtends() {
    }

    public static class Tools {

        private static ThreadLocal t1 = new ThreadLocal();
    }


    public  static ThreadLocal getInstance() {
        return Tools.t1;
    }


}