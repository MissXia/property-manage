package com.property.manage.common.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 管辉俊
 * @date 2018-05-22
 */
@JsonIgnoreProperties({"sessionKey"})
public class UserInfoView extends UserInfo {

    private String companyName;

    private String unitNumber;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
}