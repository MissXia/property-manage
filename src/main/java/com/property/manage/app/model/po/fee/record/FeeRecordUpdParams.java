package com.property.manage.app.model.po.fee.record;


import com.property.manage.base.model.model.BaseParams;

import java.math.BigDecimal;

public class FeeRecordUpdParams extends BaseParams {

    /**
     * 记录ID
     */
    private Long recordId;

    /**
     * 企业ID
     */
    private Long companyId;

    /**
     * 收费项目ID
     */
    private Long itemId;

    /**
     * 所属月份
     */
    private String theMonth;

    /**
     * 应收金额
     */
    private BigDecimal planPayFee;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getTheMonth() {
        return theMonth;
    }

    public void setTheMonth(String theMonth) {
        this.theMonth = theMonth;
    }

    public BigDecimal getPlanPayFee() {
        return planPayFee;
    }

    public void setPlanPayFee(BigDecimal planPayFee) {
        this.planPayFee = planPayFee;
    }
}