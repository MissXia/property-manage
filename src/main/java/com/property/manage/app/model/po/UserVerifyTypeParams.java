package com.property.manage.app.model.po;


import com.property.manage.common.enums.UserTypes;

public class UserVerifyTypeParams extends UserOperateParams {

    private UserTypes userType;

    public UserTypes getUserType() {
        return userType;
    }

    public void setUserType(UserTypes userType) {
        this.userType = userType;
    }
}