package com.xiazhenyu.corp.orm.plugins;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Date: 2022/12/28
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class StackTraceUtil {


    public static String current() {
        Exception exception = new Exception("设置分页参数时的堆栈信息");
        StringWriter writer = new StringWriter();
        exception.printStackTrace(new PrintWriter(writer));
        return writer.toString();

    }

}