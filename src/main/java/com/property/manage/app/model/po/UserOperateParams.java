package com.property.manage.app.model.po;


import com.property.manage.base.model.model.Request;

public class UserOperateParams extends Request {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
