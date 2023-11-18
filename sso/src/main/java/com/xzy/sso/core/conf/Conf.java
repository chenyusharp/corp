package com.xzy.sso.core.conf;

import com.xzy.sso.core.entity.ReturnT;

/**
 * Date: 2023/7/29
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Conf {


    public static final String SSO_SESSIONID = "xzy_sso_sessionid";

    public static final String REDIRECT_URL = "redirect_utl";

    public static final String SSO_USER = "sso_user";

    public static final String SSO_SERVER = "sso_server";

    public static final String SSO_LOGIN = "sso_login";

    public static final String SSO_LOGOUT = "sso_logout";


    public static final String SSO_LOGOUT_PATH = "sso_logout_path";

    public static final String SSO_EXCLUDE_PATHS = "sso_exclude_paths";

    public static final ReturnT<String> SSO_LOGIN_FAIL_RESULT = new ReturnT<>(501, "sso not login");

}



