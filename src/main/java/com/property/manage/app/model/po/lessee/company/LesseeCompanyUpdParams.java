package com.property.manage.app.model.po.lessee.company;


import com.property.manage.base.model.model.BaseParams;

public class LesseeCompanyUpdParams extends BaseParams {

    private Long companyId;

    private String companyName;

    private String unitNumber;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

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