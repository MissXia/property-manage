package com.property.manage.common.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 管辉俊
 * @date 2018-05-22
 */
@JsonIgnoreProperties({"sessionKey"})
public class UserInfoView extends UserInfo {

    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}