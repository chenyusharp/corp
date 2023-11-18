package com.xzy.sso.core.user;

import java.io.Serializable;
import java.util.Map;

/**
 * Date: 2023/7/29
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class XzySsoUser implements Serializable {


    private static final long serialVersionUID = -8715267601708355074L;


    private String userId;
    private String userName;
    private Map<String, String> pluginInfo;
    private String version;

    private int expireMinute;


    private long expireFreshTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<String, String> getPluginInfo() {
        return pluginInfo;
    }

    public void setPluginInfo(Map<String, String> pluginInfo) {
        this.pluginInfo = pluginInfo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getExpireMinute() {
        return expireMinute;
    }

    public void setExpireMinute(int expireMinute) {
        this.expireMinute = expireMinute;
    }

    public long getExpireFreshTime() {
        return expireFreshTime;
    }

    public void setExpireFreshTime(long expireFreshTime) {
        this.expireFreshTime = expireFreshTime;
    }
}