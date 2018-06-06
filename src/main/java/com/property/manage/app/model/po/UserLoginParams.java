package com.property.manage.app.model.po;


import com.property.manage.base.model.model.Request;
import com.property.manage.common.pojo.UserInfo;

public class UserLoginParams extends Request {

    private String code;

    private UserInfo userInfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
