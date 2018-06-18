package com.property.manage.app.model.po.fee.record;


import com.property.manage.base.model.model.BaseParams;

import java.math.BigDecimal;
import java.util.Date;

public class FeeRecordPayParams extends BaseParams {

    /**
     * 记录ID
     */
    private Long recordId;

    /**
     * 实收金额
     */
    private BigDecimal realPayFee;

    /**
     * 缴费时间
     */
    private Date payTime;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public BigDecimal getRealPayFee() {
        return realPayFee;
    }

    public void setRealPayFee(BigDecimal realPayFee) {
        this.realPayFee = realPayFee;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}