package com.property.manage.app.model.po.lessee.company.unit;


import com.property.manage.base.model.model.BaseParams;

public class LesseeCompanyUnitAddParams extends BaseParams {

    private Long companyId;

    private String unitNumber;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
}