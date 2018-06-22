package com.property.manage.common.pojo;

import com.property.manage.base.model.pojo.BasePojo;

import java.util.Date;

/**
 * @author 管辉俊
 * @date 2018-06-22
 */
public class FeeRecordUpload extends BasePojo {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 成员id
     */
    private Long userId;

    /**
     * 总共条数
     */
    private Integer totalCount;

    /**
     * 失败条数
     */
    private Integer errorCount;

    /**
     * 失败json
     */
    private String errorJson;

    /**
     * 状态:-1、导入失败 0、正在导入 1、导入成功 2、部分成功
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 最后修改时间
     */
    private Date modified;

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
     * @return userId 成员id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId 成员id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return totalCount 总共条数
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount 总共条数
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return errorCount 失败条数
     */
    public Integer getErrorCount() {
        return errorCount;
    }

    /**
     * @param errorCount 失败条数
     */
    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    /**
     * @return errorJson 失败json
     */
    public String getErrorJson() {
        return errorJson;
    }

    /**
     * @param errorJson 失败json
     */
    public void setErrorJson(String errorJson) {
        this.errorJson = errorJson;
    }

    /**
     * @return status 状态:-1、导入失败 0、正在导入 1、导入成功 2、部分成功
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 状态:-1、导入失败 0、正在导入 1、导入成功 2、部分成功
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return created 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return modified 最后修改时间
     */
    public Date getModified() {
        return modified;
    }

    /**
     * @param modified 最后修改时间
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

}