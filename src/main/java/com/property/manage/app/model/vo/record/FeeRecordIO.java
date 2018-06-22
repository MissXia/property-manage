package com.property.manage.app.model.vo.record;

import java.math.BigDecimal;

/**
 * @author 管辉俊
 * @date 2018-06-10
 */
public class FeeRecordIO {

    /**
     * 所属月份
     */
    private String theMonth;

    /**
     * 应收金额
     */
    private BigDecimal planPayFee;

    /**
     * 收费项目名称
     */
    private String itemName;

    /**
     * 手机号码
     */
    private String phoneNumber;

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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}