package com.xzy.sso.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Date: 2023/7/30
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class CookieUtil {

    public static final int COOKIE_MAX_AGE = Integer.MAX_VALUE;
    public static final String COOKIE_PATH = "/";


    public static void set(HttpServletResponse response, String key, String value, boolean isRemeber) {

        final int age = isRemeber ? COOKIE_MAX_AGE : -1;
        set(response, key, value, null, COOKIE_PATH, age, true);


    }

    private static void set(HttpServletResponse response, String key, String value, String domain, String path, int maxAge, boolean isHttpOnly) {
        Cookie cookie = new Cookie(key, value);
        if (domain != null) {
            cookie.setDomain(domain);
        }
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(isHttpOnly);
        response.addCookie(cookie);
    }

    public static String getValue(HttpServletRequest request, String key) {
        Cookie cookie = get(request, key);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    private static Cookie get(HttpServletRequest request, String key) {
        final Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie;
                }
            }
        }
        return null;
    }


    public static void remove(HttpServletRequest request, HttpServletResponse response, String key) {
        final Cookie cookie = get(request, key);
        if (cookie != null) {
            set(response, key, "", null, COOKIE_PATH, 0, true);
        }

    }


}