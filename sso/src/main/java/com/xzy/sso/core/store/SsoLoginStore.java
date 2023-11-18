package com.xzy.sso.core.store;

import com.xzy.sso.core.conf.Conf;
import com.xzy.sso.core.user.XzySsoUser;
import com.xzy.sso.core.util.JedisUtil;

/**
 * Date: 2023/7/29
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class SsoLoginStore {


    private static int redisExpireMinute = 1440;

    public static void setRedisExpireMinute(int redisExpireMinute) {
        if (redisExpireMinute < 30) {
            redisExpireMinute = 30;
        }
        SsoLoginStore.redisExpireMinute = redisExpireMinute;
    }

    public static int setRedisExpireMinute() {
        return redisExpireMinute;
    }

    public static XzySsoUser get(String storeKey) {
        final String redisKey = redisKey(storeKey);
        final Object objectValue = JedisUtil.getObjectValue(redisKey);
        if (objectValue != null) {
            final XzySsoUser xzySsoUser = (XzySsoUser) objectValue;
            return xzySsoUser;
        }
        return null;
    }

    private static String redisKey(String sesionId) {
        return Conf.SSO_SESSIONID.concat("#").concat(sesionId);
    }

    public static void remove(String storeKey) {
        final String redisKey = redisKey(storeKey);
        JedisUtil.del(redisKey);
    }

    public static void put(String storeKey, XzySsoUser xzySsoUser) {
        final String redisKey = redisKey(storeKey);
        JedisUtil.setObjectValue(redisKey, xzySsoUser, redisExpireMinute * 60);
    }


}