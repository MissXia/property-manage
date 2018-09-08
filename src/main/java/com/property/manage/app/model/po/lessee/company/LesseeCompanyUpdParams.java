package com.property.manage.app.model.po.lessee.company;


import com.property.manage.base.model.model.BaseParams;

public class LesseeCompanyUpdParams extends BaseParams {

    private Long companyId;

    private String companyName;

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
}