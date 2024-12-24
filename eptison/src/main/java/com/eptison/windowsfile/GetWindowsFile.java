package com.eptison.windowsfile;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Date: 2024/11/16
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class GetWindowsFile {


    public static void main(String[] args) throws JSchException, SftpException {
        String remoteUrl = "smb://administrator:q54NeK8RVpXx@bjpostest.yptcgroup.com:20000/<path-to-file>";

        String filePath = "D:\\BOS3\\tomcat8\\webapps\\ROOT\\img\\M_PRODUCT_COLOR\\";
        JSch jSch = new JSch();
        Session session = null;
        session = jSch.getSession("administrator", "bjpostest.yptcgroup.com",20000);

        // 设置登陆主机的密码
        session.setPassword("q54NeK8RVpXx");// 设置密码

        session.setConfig("StrictHostKeyChecking", "no");

        session.connect(300000);
        Channel channel = session.openChannel("sftp");
        channel.connect(10000000);
        ChannelSftp sftp = (ChannelSftp) channel;
        try {
            sftp.cd(filePath);
        } catch (SftpException e) {
            throw new RuntimeException(e);
        }
        //  读取本地的文件
        File nativeFile = new File("/Users/xiazhenyu/Desktop/Eptison/图片/15.jpg");

        File file = new File(filePath + "\\" + "15.jpg");
        OutputStream o = sftp.put(file.getName());
        try {
            o.write(nativeFile.toString().getBytes("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            o.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        session.disconnect();
        channel.disconnect();
    }

}