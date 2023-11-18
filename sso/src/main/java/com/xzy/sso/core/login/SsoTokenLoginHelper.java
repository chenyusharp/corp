package com.xzy.sso.core.login;

import com.xzy.sso.core.conf.Conf;
import com.xzy.sso.core.store.SsoLoginStore;
import com.xzy.sso.core.store.SsoSessionHelper;
import com.xzy.sso.core.user.XzySsoUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Date: 2023/7/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SsoTokenLoginHelper {


    public static void login(String sessionId, XzySsoUser xzySsoUser) {
        final String storeKey = SsoSessionHelper.parseStoreKey(sessionId);
        if (storeKey == null) {
            throw new RuntimeException("parseStoreKey fail,sessionID:" + sessionId);
        }
        SsoLoginStore.put(storeKey, xzySsoUser);
    }


    public static void logout(String sessionId) {
        final String storeKey = SsoSessionHelper.parseStoreKey(sessionId);
        if (storeKey == null) {
            return;
        }
        SsoLoginStore.remove(storeKey);
    }


    public static void logout(HttpServletRequest request) {
        final String headerSessionId = request.getHeader(Conf.SSO_SESSIONID);
        logout(headerSessionId);
    }

    public static XzySsoUser loginCheck(String sessionId) {
        final String storeKey = SsoSessionHelper.parseStoreKey(sessionId);
        if (storeKey == null) {
            return null;
        }
        final XzySsoUser xzySsoUser = SsoLoginStore.get(storeKey);
        if (xzySsoUser != null) {
            final String version = SsoSessionHelper.parseVersion(sessionId);
            if (xzySsoUser.getVersion().equals(version)) {
                if ((System.currentTimeMillis() - xzySsoUser.getExpireFreshTime()) > xzySsoUser.getExpireMinute() / 2) {
                    xzySsoUser.setExpireFreshTime(System.currentTimeMillis());
                    SsoLoginStore.put(storeKey, xzySsoUser);
                }
                return xzySsoUser;
            }
        }
        return null;
    }

    public static XzySsoUser loginCheck(HttpServletRequest request) {
        final String headerSessionId = request.getHeader(Conf.SSO_SESSIONID);
        return loginCheck(headerSessionId);
    }


}