package com.xiazhenyu.wheat;

/**
 * @Author xiazhenyu
 * @Description
 * @Date 12:05 上午 2021/6/2
 **/
public class Fibonacci {


    public int fibonacci(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n - 2) + fibonacci(n - 1);


    }

}
