package com.xzy.sso.server.service.impl;

import com.xzy.sso.core.entity.ReturnT;
import com.xzy.sso.server.service.UserService;
import com.xzy.sso.server.service.model.UserInfo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Date: 2023/7/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Service
public class UserviceImpl implements UserService {


    private static List<UserInfo> mockUserList = new ArrayList<>();

    static {
        for (int i = 0; i < 5; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(1000 + i);
            userInfo.setUserName("user" + (i > 0 ? String.valueOf(i) : ""));
            userInfo.setPassword("123456");
            mockUserList.add(userInfo);
        }
    }


    @Override
    public ReturnT<UserInfo> findUser(String userName, String password) {
        if (userName == null || userName.trim().length() == 0) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "Please input username.");
        }
        if (password==null || password.trim().length()==0) {
            return new ReturnT<UserInfo>(ReturnT.FAIL_CODE, "Please input password.");
        }
        // mock user
        for (UserInfo mockUser: mockUserList) {
            if (mockUser.getUserName().equals(userName) && mockUser.getPassword().equals(password)) {
                return new ReturnT<UserInfo>(mockUser);
            }
        }

        return new ReturnT<UserInfo>(ReturnT.FAIL_CODE, "username or password is invalid.");
    }
}