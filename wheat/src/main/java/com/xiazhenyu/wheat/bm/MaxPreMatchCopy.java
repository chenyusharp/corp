package com.xiazhenyu.wheat.bm;

import com.sun.xml.internal.ws.util.StringUtils;
import java.util.Arrays;

/**
 * Date: 2021/10/4
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class MaxPreMatchCopy {


    public static String maxPreMatch(String[] strArray) {
        if (strArray.length == 0) {
            return "";
        }
        //字典排序
        Arrays.sort(strArray);
        int length = strArray.length;
        //获取开始、结束的字符长度
        char[] startStr = strArray[0].toCharArray();
        char[] endStr = strArray[length - 1].toCharArray();
        //最长的匹配字符
        int num = Integer.max(startStr.length, endStr.length);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            if (startStr[i] == endStr[i]) {
                stringBuilder.append(startStr[i]);
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        String[] stringArray = new String[]{"xiazhenyu", "xiaoming", "xisan", "xiyang"};
        System.out.println(maxPreMatch(stringArray));
    }

}