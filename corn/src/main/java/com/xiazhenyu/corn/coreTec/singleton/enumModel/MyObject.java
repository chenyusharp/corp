package com.xiazhenyu.corn.coreTec.singleton.enumModel;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;

/**
 * Date: 2022/1/3
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public enum MyObject {

    userInfoFactory;

    //更多的时候，这个地方会去初始化一些数据库链接之类的。
    private UserInfo userInfo;


    MyObject() {
        userInfo = new UserInfo("xiazhenyu", "123456");
    }


    public static UserInfo getUserInfo() {
        return userInfoFactory.userInfo;
    }


    public class UserInfo {

        private String userName;
        private String passWord;

        public UserInfo(String userName, String passWord) {
            this.userName = userName;
            this.passWord = passWord;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassWord() {
            return passWord;
        }

        public void setPassWord(String passWord) {
            this.passWord = passWord;
        }
    }
}



