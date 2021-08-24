package com.xiazhenyu.registry;

import java.io.Serializable;

/**
 * Date: 2021/8/17
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ServerInfo implements Serializable {

    private String host;
    private int port;

    public ServerInfo() {
    }


    public ServerInfo(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
