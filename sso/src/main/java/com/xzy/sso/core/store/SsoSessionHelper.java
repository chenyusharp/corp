package com.xzy.sso.core.store;

import com.xzy.sso.core.user.XzySsoUser;

/**
 * Date: 2023/7/29
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SsoSessionHelper {


    public static String makeSessionId(XzySsoUser xzySsoUser) {
        final String sessionId = xzySsoUser.getUserId().concat("_").concat(xzySsoUser.getVersion());
        return sessionId;
    }


    public static String parseStoreKey(String sessionId) {
        if (sessionId != null && sessionId.indexOf("_") > -1) {
            final String[] sessionIdArr = sessionId.split("_");
            if (sessionIdArr.length == 2 && sessionIdArr[0] != null && sessionIdArr[0].trim().length() > 0) {
                final String userId = sessionIdArr[0].trim();
                return userId;
            }
        }
        return null;
    }


    public static String parseVersion(String sessionId) {
        if (sessionId != null && sessionId.indexOf("_") > -1) {
            final String[] sessionIdArr = sessionId.split("_");
            if (sessionIdArr.length == 2 && sessionIdArr[1] != null
                    && sessionIdArr[1].trim().length() > 0) {
                final String version = sessionIdArr[1].trim();
                return version;
            }
        }
        return null;
    }


}