package com.property.manage.common.pojo;

import com.property.manage.base.model.pojo.BasePojo;

import java.util.Date;

/**
 * @author 管辉俊
 * @date 2018-06-10
 */
public class FeeItem extends BasePojo {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 项目名称
     */
    private String itemName;

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
     * @return itemName 项目名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName 项目名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
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