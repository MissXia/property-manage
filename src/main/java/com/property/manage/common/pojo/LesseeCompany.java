package com.property.manage.common.pojo;

import com.property.manage.base.model.pojo.BasePojo;

import java.util.Date;

/**
 * @author 管辉俊
 * @date 2018-08-20
 */
public class LesseeCompany extends BasePojo {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 单元编号
     */
    private String unitNumber;

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

    /**
     * @return companyName 企业名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName 企业名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return unitNumber 单元编号
     */
    public String getUnitNumber() {
        return unitNumber;
    }

    /**
     * @param unitNumber 单元编号
     */
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
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