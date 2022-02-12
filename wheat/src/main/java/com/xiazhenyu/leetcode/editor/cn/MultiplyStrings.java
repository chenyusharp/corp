//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
//
// 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。 
//
// 
//
// 示例 1: 
//
// 
//输入: num1 = "2", num2 = "3"
//输出: "6" 
//
// 示例 2: 
//
// 
//输入: num1 = "123", num2 = "456"
//输出: "56088" 
//
// 
//
// 提示： 
//
// 
// 1 <= num1.length, num2.length <= 200 
// num1 和 num2 只能由数字组成。 
// num1 和 num2 都不包含任何前导零，除了数字0本身。 
// 
// Related Topics 数学 字符串 模拟 👍 828 👎 0

package com.xiazhenyu.leetcode.editor.cn;

public class MultiplyStrings {

    public static void main(String[] args) {
        Solution solution = new MultiplyStrings().new Solution();

        System.out.println('W' - 'U');
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public String multiply(String num1, String num2) {

            if (num1.equals("0") || num2.equals("0")) {
                return "0";
            }
            int m = num1.length(), n = num2.length();
            //证明出相乘之后的结果的位数不会超过m+n位
            int ansArr[] = new int[m + n];
            for (int i = m - 1; i >= 0; i--) {
                //核心判断，字符串类型的数字如何在不使用Integer相关函数的情况下得到数字值？
                //可以通过char类型相减得到，就是chatAt得到对应的char，然后减去'0',
                //这个操作的本质是什么呢？其实就是通过ASCII的值相减得到的。通过距离差得到对应的数值。
                int x = num1.charAt(i) - '0';
                for (int j = n - 1; j >= 0; j--) {
                    int y = num2.charAt(j) - '0';
                    ansArr[i + j + 1] += x * y;
                }
            }
            for (int i = m + n - 1; i > 0; i--) {
                ansArr[i - 1] += ansArr[i] / 10;
                ansArr[i] %= 10;
            }
            //从第一个首位不是0的下标开始
            int index = ansArr[0] == 0 ? 1 : 0;
            StringBuffer sb = new StringBuffer();
            while (index < m + n) {
                sb.append(ansArr[index]);
                index++;
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}