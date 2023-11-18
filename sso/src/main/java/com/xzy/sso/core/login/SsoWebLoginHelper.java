package com.xzy.sso.core.login;

import com.xzy.sso.core.conf.Conf;
import com.xzy.sso.core.store.SsoLoginStore;
import com.xzy.sso.core.store.SsoSessionHelper;
import com.xzy.sso.core.user.XzySsoUser;
import com.xzy.sso.core.util.CookieUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Date: 2023/7/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SsoWebLoginHelper {

    public static void login(HttpServletResponse response, String sessionId, XzySsoUser xzySsoUser, boolean ifRemember) {

        final String storeKey = SsoSessionHelper.parseStoreKey(sessionId);
        if (storeKey == null) {
            throw new RuntimeException("parseStoreKey fail,sessionId:" + sessionId);
        }
        SsoLoginStore.put(storeKey, xzySsoUser);
        CookieUtil.set(response, Conf.SSO_SESSIONID, sessionId, ifRemember);
    }

    public static void logout(HttpServletRequest request, HttpServletResponse response) {

        final String cookieSessionId = CookieUtil.getValue(request, Conf.SSO_SESSIONID);
        if (cookieSessionId == null) {
            return;
        }

        final String storeKey = SsoSessionHelper.parseStoreKey(cookieSessionId);
        if (storeKey != null) {
            SsoLoginStore.remove(storeKey);
        }
        CookieUtil.remove(request, response, Conf.SSO_SESSIONID);
    }


    public static XzySsoUser loginCheck(HttpServletRequest request, HttpServletResponse response) {
        final String cookieSessionId = CookieUtil.getValue(request, Conf.SSO_SESSIONID);
        XzySsoUser xzySsoUser = SsoTokenLoginHelper.loginCheck(cookieSessionId);
        if (xzySsoUser != null) {
            return xzySsoUser;
        }
        SsoWebLoginHelper.removeSessionIdByCookie(request, response);
        final String paramSessionId = request.getParameter(Conf.SSO_SESSIONID);
        xzySsoUser = SsoTokenLoginHelper.loginCheck(paramSessionId);
        if (xzySsoUser != null) {
            CookieUtil.set(response, Conf.SSO_SESSIONID, paramSessionId, false);
            return xzySsoUser;
        }
        return null;
    }


    public static void removeSessionIdByCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.remove(request, response, Conf.SSO_SESSIONID);
    }

    public static String getSessinIdBYCookie(HttpServletRequest request) {
        final String cookieSessinId = CookieUtil.getValue(request, Conf.SSO_SESSIONID);
        return cookieSessinId;
    }


}


