//给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
//
// 返回被除数 dividend 除以除数 divisor 得到的商。 
//
// 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2 
//
// 
//
// 示例 1: 
//
// 输入: dividend = 10, divisor = 3
//输出: 3
//解释: 10/3 = truncate(3.33333..) = truncate(3) = 3 
//
// 示例 2: 
//
// 输入: dividend = 7, divisor = -3
//输出: -2
//解释: 7/-3 = truncate(-2.33333..) = -2 
//
// 
//
// 提示： 
//
// 
// 被除数和除数均为 32 位有符号整数。 
// 除数不为 0。 
// 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2³¹, 231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。 
// 
// Related Topics 位运算 数学 👍 822 👎 0

package com.xiazhenyu.leetcode.editor.cn;

public class DivideTwoIntegers {

    public static void main(String[] args) {
        Solution solution = new DivideTwoIntegers().new Solution();

        int a = 1;
        int b = 1;
        System.out.println(a ^ b);

//        System.out.println(solution.divide(10, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {

        public int divide(int dividend, int divisor) {

            /*int sign = (dividend ^ divisor) >= 0 ? 1 : -1;
            long dividendTemp = Math.abs(dividend);
            long divisorTmp = Math.abs(divisor);
            long res = 0;
            while (dividendTemp >= divisorTmp) {
                long temp = divisorTmp;
                long times = 1;
                while (dividendTemp >= (temp << 1)) {
                    temp <<= 1;
                    times <<= 1;
                }
                dividendTemp -= temp;
                res += times;
            }
            res = sign > 0 ? res : -res;
            return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;*/

            /*-- 第一种解法--*/
            /*int sign = (dividend ^ divisor) >= 0 ? 1 : -1;
            dividend = -Math.abs(dividend);
            divisor = -Math.abs(divisor);
            int res = 0;
            int threshold = Integer.MIN_VALUE >> 1;
            while (dividend <= divisor) {
                int tmp = divisor;
                int times = 1;
                while (tmp >= threshold && dividend <= (tmp << 1)) {
                    tmp <<= 1;
                    times <<= 1;
                }
                dividend -= tmp;
                res -= times;
            }
            if (res == Integer.MIN_VALUE && sign == 1) {
                return Integer.MAX_VALUE;
            }
            return sign < 0 ? res : -res;*/

            boolean sign = (dividend ^ divisor) >= 0;
            dividend = dividend < 0 ? dividend : ~subtraction(dividend, 1);
            divisor = divisor < 0 ? divisor : ~subtraction(divisor, 1);
            int res = 0;
            int threshold = Integer.MIN_VALUE >> 1;
            while (dividend <= divisor) {
                int tmp = divisor;
                int times = 1;
                while (tmp >= threshold && dividend <= (divisor << 1)) {
                    tmp <<= 1;
                    times <<= 1;
                }
                dividend = subtraction(dividend, tmp);
                res = subtraction(res, times);
            }
            if (res == Integer.MIN_VALUE && sign) {
                return Integer.MAX_VALUE;
            }
            return !sign ? res : ~subtraction(res, 1);


        }

        private int subtraction(int a, int b) {
            if (b == 0) {
                return a;
            }
            int c = a & b;
            a ^= c;
            b ^= c;
            return subtraction(a | b, b << 1);
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}