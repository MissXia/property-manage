package com.property.manage.app.model.vo.record;

import java.math.BigDecimal;
import java.util.Date;

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
     * 单元编号
     */
    private String unitNumber;

    /**
     * 实收金额
     */
    private BigDecimal realPayFee;

    /**
     * 缴费时间
     */
    private Date payTime;

    /**
     * 缴费状态：0、未缴费 1、已缴费
     */
    private String payStatus;

    /**
     * 开票时间
     */
    private Date ticketTime;

    /**
     * 开票状态：0、未开票 1、已开票
     */
    private String ticketStatus;

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

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
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

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Date getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(Date ticketTime) {
        this.ticketTime = ticketTime;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}