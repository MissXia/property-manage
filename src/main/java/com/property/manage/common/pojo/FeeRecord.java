package com.property.manage.common.pojo;

import com.property.manage.base.model.pojo.BasePojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 管辉俊
 * @date 2018-06-10
 */
public class FeeRecord extends BasePojo {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Long id;

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
    private Integer payStatus;

    /**
     * 开票时间
     */
    private Date ticketTime;

    /**
     * 开票状态：0、未开票 1、已开票
     */
    private Integer ticketStatus;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 修改时间
     */
    private Date updTime;

    /**
     * @return id 自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 自增主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * @return itemId 收费项目ID
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * @param itemId 收费项目ID
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * @return theMonth 所属月份
     */
    public String getTheMonth() {
        return theMonth;
    }

    /**
     * @param theMonth 所属月份
     */
    public void setTheMonth(String theMonth) {
        this.theMonth = theMonth;
    }

    /**
     * @return planPayFee 应收金额
     */
    public BigDecimal getPlanPayFee() {
        return planPayFee;
    }

    /**
     * @param planPayFee 应收金额
     */
    public void setPlanPayFee(BigDecimal planPayFee) {
        this.planPayFee = planPayFee;
    }

    /**
     * @return realPayFee 实收金额
     */
    public BigDecimal getRealPayFee() {
        return realPayFee;
    }

    /**
     * @param realPayFee 实收金额
     */
    public void setRealPayFee(BigDecimal realPayFee) {
        this.realPayFee = realPayFee;
    }

    /**
     * @return payTime 缴费时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * @param payTime 缴费时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * @return payStatus 缴费状态：0、未缴费 1、已缴费
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * @param payStatus 缴费状态：0、未缴费 1、已缴费
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * @return ticketTime 开票时间
     */
    public Date getTicketTime() {
        return ticketTime;
    }

    /**
     * @param ticketTime 开票时间
     */
    public void setTicketTime(Date ticketTime) {
        this.ticketTime = ticketTime;
    }

    /**
     * @return ticketStatus 开票状态：0、未开票 1、已开票
     */
    public Integer getTicketStatus() {
        return ticketStatus;
    }

    /**
     * @param ticketStatus 开票状态：0、未开票 1、已开票
     */
    public void setTicketStatus(Integer ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    /**
     * @return addTime 创建时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime 创建时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return updTime 修改时间
     */
    public Date getUpdTime() {
        return updTime;
    }

    /**
     * @param updTime 修改时间
     */
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

}