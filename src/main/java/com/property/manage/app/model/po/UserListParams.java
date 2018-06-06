package com.property.manage.app.model.po;


import com.property.manage.base.model.model.BaseParams;

public class UserListParams extends BaseParams {

    private Integer userType;

    private String nickName;

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}