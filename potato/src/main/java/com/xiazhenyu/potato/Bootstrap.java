package com.xiazhenyu.potato;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Date: 2021/9/20
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Bootstrap {


    private int port = 8080;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    public void start() throws IOException {

        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("======> potato start on port:" + port);
        /**while (true) {
         Socket socket = serverSocket.accept();
         //接受请求
         OutputStream outputStream = socket.getOutputStream();
         String data = "Hello potato";
         String responseText = HttpProtocolUtil.getHttpHeader200(data.getBytes().length) + data;
         outputStream.write(responseText.getBytes());
         serverSocket.close();
         }**/
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            socket.close();

        }
    }

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}