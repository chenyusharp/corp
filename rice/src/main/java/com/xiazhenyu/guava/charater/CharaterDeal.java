package com.xiazhenyu.guava.charater;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Date: 2023/2/5
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class CharaterDeal {


    public static void main(String[] args) {
        Joiner joner = Joiner.on("; ").skipNulls();
        System.out.println(joner.join("Harry", null, "Ron", "Hermione"));

        System.out.println(Joiner.on(",").join(Arrays.asList(1, 5, 7)));

        System.out.println(Splitter.on(",").trimResults().omitEmptyStrings().split("foo,bar,, qux"));

        System.out.println(Splitter.onPattern("\r?\n").trimResults().omitEmptyStrings().split("hah nihao "
                + "you this"));

        //CharMatcher
        String noControl = CharMatcher.javaIsoControl().removeFrom("control is a bad "); //移除control字符
        System.out.println(noControl);
        String theDigits = CharMatcher.javaDigit().retainFrom("htller 123"); //只保留数字字符
        System.out.println(theDigits);
        String spaced = CharMatcher.whitespace().trimAndCollapseFrom("dfdf       hee", ' ');
        System.out.println(spaced);
        //去除两端的空格，并把中间的连续空格替换成单个空格
        String noDigits = CharMatcher.javaDigit().replaceFrom("hello 123", "*"); //用*号替换所有数字
        System.out.println(noDigits);
        String lowerAndDigit = CharMatcher.javaDigit().or(CharMatcher.javaLowerCase()).retainFrom("HELLO hello");// 只保留数字和小写字母
        System.out.println(lowerAndDigit);

        System.out.println("hello ,nihao".getBytes(Charsets.UTF_8));

        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME"));


    }


}