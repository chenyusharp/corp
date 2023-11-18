package com.xzy.sso.server.service;

import com.xzy.sso.core.entity.ReturnT;
import com.xzy.sso.server.service.model.UserInfo;

/**
 * Date: 2023/7/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public interface UserService {

    ReturnT<UserInfo> findUser(String userName, String password);


}
