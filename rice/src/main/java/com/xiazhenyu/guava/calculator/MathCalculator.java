package com.xiazhenyu.guava.calculator;

import static java.math.RoundingMode.CEILING;
import static java.math.RoundingMode.HALF_DOWN;
import static java.math.RoundingMode.HALF_UP;

import com.google.common.math.BigIntegerMath;
import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Date: 2023/2/5
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MathCalculator {


    public static void main(String[] args) {

        System.out.println(LongMath.log2(9, RoundingMode.FLOOR));

        System.out.println(IntMath.checkedMultiply(2, 4));

        System.out.println(LongMath.divide(7, 2, HALF_DOWN));

        System.out.println(DoubleMath.roundToBigInteger(12, RoundingMode.HALF_EVEN));

//        final BigDecimal a = new BigDecimal(12);
//        final BigDecimal b = new BigDecimal(2);
//        System.out.println(a.divide(b).setScale(2,HALF_UP));

        System.out.println(BigIntegerMath.sqrt(BigInteger.valueOf(9), CEILING));


    }


}