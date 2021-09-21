package com.xiazhenyu.potato;

/**
 * Date: 2021/9/20
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class HttpProtocolUtil {


    public static String getHttpHeader200(long length) {
        return "HTTP/1.1 200 OK \n" +
                "Content-Type text/html \n"
                + "Content-Length: " + length + "\n"
                + "\r\n";
    }


    public static String getHttpHeader404() {
        String str404 = "<h1>404 not found</h1>";
        return "HTTP/1.1 404 NOT Found \n" +
                "Content-Type text/html \n"
                + "Content-Length: " + str404.getBytes().length + "\n"
                + "\r\n" + str404;
    }


}