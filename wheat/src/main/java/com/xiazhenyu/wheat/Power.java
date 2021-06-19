package com.xiazhenyu.wheat;

/**
 * @Author xiazhenyu
 * @Description
 * @Date 12:22 上午 2021/6/5
 **/
public class Power {


    boolean invalidInpuit = false;

    public double power(double base, int exponent) {

        if (equal(base, 0.0) && exponent < 0) {
            invalidInpuit = true;
            return 0.0;
        }
        int absexponent = exponent;
        if (exponent < 0) {
            absexponent = -exponent;
        }
        double res = getPower(base, absexponent);
        if (exponent < 0) {
            res = 1.0 / res;
        }
        return res;


    }


    public boolean equal(double num1, double num2) {
        if (num1 - num2 > -0.000001 && num1 - num2 < 0.000001) {
            return true;
        } else {
            return false;
        }

    }


    double getPower(double b, int e) {
        if (e == 0) {
            return 1.0;
        }
        if (e == 1) {
            return b;
        }
        double result = getPower(b, e >> 1);
        result *= result;
        if ((e & 1) == 1) {
            result *= b;
        }
        return result;


    }

}
