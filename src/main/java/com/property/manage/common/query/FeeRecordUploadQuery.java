package com.property.manage.common.query;

import com.property.manage.base.model.enums.SortDirection;
import com.property.manage.base.model.model.OrderField;
import com.property.manage.base.model.query.BaseQuery;

import java.util.Date;
import java.util.Map;


/**
 * @author 管辉俊
 */
public class FeeRecordUploadQuery extends BaseQuery {

    /**==============================批量查询、更新、删除时的Where条件设置==================================**/
    /**
     * 自增主键
     **/
    private Long id;

    public Long getId() {
        return id;
    }

    public FeeRecordUploadQuery setId(Long id) {
        this.id = id;
        this.params.put("id", id);
        return this;
    }

    /**
     * 成员id
     **/
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public FeeRecordUploadQuery setUserId(Long userId) {
        this.userId = userId;
        this.params.put("userId", userId);
        return this;
    }

    /**
     * 总共条数
     **/
    private Integer totalCount;

    public Integer getTotalCount() {
        return totalCount;
    }

    public FeeRecordUploadQuery setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        this.params.put("totalCount", totalCount);
        return this;
    }

    /**
     * 失败条数
     **/
    private Integer errorCount;

    public Integer getErrorCount() {
        return errorCount;
    }

    public FeeRecordUploadQuery setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
        this.params.put("errorCount", errorCount);
        return this;
    }

    /**
     * 失败json
     **/
    private String errorJson;

    public String getErrorJson() {
        return errorJson;
    }

    public FeeRecordUploadQuery setErrorJson(String errorJson) {
        this.errorJson = errorJson;
        this.params.put("errorJson", errorJson);
        return this;
    }

    /**
     * 状态:-1、导入失败 0、正在导入 1、导入成功 2、部分成功
     **/
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public FeeRecordUploadQuery setStatus(Integer status) {
        this.status = status;
        this.params.put("status", status);
        return this;
    }

    /**
     * 创建时间
     **/
    private Date createdStart;

    public Date getCreatedStart() {
        return createdStart;
    }

    public FeeRecordUploadQuery setCreatedStart(Date created) {
        this.createdStart = created;
        this.params.put("createdStart", created);
        return this;
    }

    private Date createdEnd;

    public Date getCreatedEnd() {
        return createdEnd;
    }

    public FeeRecordUploadQuery setCreatedEnd(Date created) {
        this.createdEnd = created;
        this.params.put("createdEnd", created);
        return this;
    }

    private Date createdEqual;

    public Date getCreatedEqual() {
        return createdEqual;
    }

    public FeeRecordUploadQuery setCreatedEqual(Date created) {
        this.createdEqual = created;
        this.params.put("createdEqual", created);
        return this;
    }

    /**
     * 最后修改时间
     **/
    private Date modifiedStart;

    public Date getModifiedStart() {
        return modifiedStart;
    }

    public FeeRecordUploadQuery setModifiedStart(Date modified) {
        this.modifiedStart = modified;
        this.params.put("modifiedStart", modified);
        return this;
    }

    private Date modifiedEnd;

    public Date getModifiedEnd() {
        return modifiedEnd;
    }

    public FeeRecordUploadQuery setModifiedEnd(Date modified) {
        this.modifiedEnd = modified;
        this.params.put("modifiedEnd", modified);
        return this;
    }

    private Date modifiedEqual;

    public Date getModifiedEqual() {
        return modifiedEqual;
    }

    public FeeRecordUploadQuery setModifiedEqual(Date modified) {
        this.modifiedEqual = modified;
        this.params.put("modifiedEqual", modified);
        return this;
    }
    /**==============================批量查询时的Order条件顺序设置==================================**/
    /**
     * 设置排序按属性：自增主键
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordUploadQuery orderbyId(boolean isAsc) {
        orderFields.add(new OrderField("id", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：成员id
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordUploadQuery orderbyUserId(boolean isAsc) {
        orderFields.add(new OrderField("user_id", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：总共条数
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordUploadQuery orderbyTotalCount(boolean isAsc) {
        orderFields.add(new OrderField("total_count", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：失败条数
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordUploadQuery orderbyErrorCount(boolean isAsc) {
        orderFields.add(new OrderField("error_count", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：失败json
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordUploadQuery orderbyErrorJson(boolean isAsc) {
        orderFields.add(new OrderField("error_json", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：状态:-1、导入失败 0、正在导入 1、导入成功 2、部分成功
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordUploadQuery orderbyStatus(boolean isAsc) {
        orderFields.add(new OrderField("status", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：创建时间
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordUploadQuery orderbyCreated(boolean isAsc) {
        orderFields.add(new OrderField("created", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：最后修改时间
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordUploadQuery orderbyModified(boolean isAsc) {
        orderFields.add(new OrderField("modified", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    @Override
    public Map<String, String> getFieldSet() {
        super.getFieldSet();
        fieldMap.put("id", "id");
        fieldMap.put("user_id", "userId");
        fieldMap.put("total_count", "totalCount");
        fieldMap.put("error_count", "errorCount");
        fieldMap.put("error_json", "errorJson");
        fieldMap.put("status", "status");
        fieldMap.put("created", "created");
        fieldMap.put("modified", "modified");
        return fieldMap;
    }
}
