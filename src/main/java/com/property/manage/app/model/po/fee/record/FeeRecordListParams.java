package com.property.manage.app.model.po.fee.record;


import com.property.manage.base.model.model.BaseParams;

import java.util.Date;

public class FeeRecordListParams extends BaseParams {

    /**
     * 收费项目名称
     */
    private String itemName;

    /**
     * 企业昵称
     */
    private String companyName;

    /**
     * 单元编号
     */
    private String unitNumber;

    /**
     * 所属月份
     */
    private String theMonth;

    /**
     * 缴费状态
     */
    private Integer payStatus;

    /**
     * 缴费时间
     */
    private Date payTimeFrom;

    /**
     * 缴费时间
     */
    private Date payTimeTo;

    /**
     * 开票时间
     */
    private Date ticketTimeFrom;

    /**
     * 开票时间
     */
    private Date ticketTimeTo;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public String getTheMonth() {
        return theMonth;
    }

    public void setTheMonth(String theMonth) {
        this.theMonth = theMonth;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Date getPayTimeFrom() {
        return payTimeFrom;
    }

    public void setPayTimeFrom(Date payTimeFrom) {
        this.payTimeFrom = payTimeFrom;
    }

    public Date getPayTimeTo() {
        return payTimeTo;
    }

    public void setPayTimeTo(Date payTimeTo) {
        this.payTimeTo = payTimeTo;
    }

    public Date getTicketTimeFrom() {
        return ticketTimeFrom;
    }

    public void setTicketTimeFrom(Date ticketTimeFrom) {
        this.ticketTimeFrom = ticketTimeFrom;
    }

    public Date getTicketTimeTo() {
        return ticketTimeTo;
    }

    public void setTicketTimeTo(Date ticketTimeTo) {
        this.ticketTimeTo = ticketTimeTo;
    }
}