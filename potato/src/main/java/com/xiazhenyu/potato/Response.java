package com.xiazhenyu.potato;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Date: 2021/9/21
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Response {

    private OutputStream outputStream;

    public Response() {
    }

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }


    public void output(String content) throws IOException {
        outputStream.write(content.getBytes());
    }

    public void outputHtml(String path) throws IOException {
        String absoluteResourcePath = StaticResourceUtil.getAbsolutePath(path);
        File file = new File(absoluteResourcePath);
        if (file.exists()) {
            StaticResourceUtil.outputStaticResource(new FileInputStream(file), outputStream);
        } else {
            output(HttpProtocolUtil.getHttpHeader404());
        }

    }


}