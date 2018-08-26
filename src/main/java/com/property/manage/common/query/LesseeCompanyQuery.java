package com.property.manage.common.query;

import com.property.manage.base.model.enums.SortDirection;
import com.property.manage.base.model.model.OrderField;
import com.property.manage.base.model.query.BaseQuery;

import java.util.Date;
import java.util.Map;


/**
 * @author 管辉俊
 */
public class LesseeCompanyQuery extends BaseQuery {

    /**==============================批量查询、更新、删除时的Where条件设置==================================**/
    /**
     * 自增主键
     **/
    private Long id;

    public Long getId() {
        return id;
    }

    public LesseeCompanyQuery setId(Long id) {
        this.id = id;
        this.params.put("id", id);
        return this;
    }

    /**
     * 企业名称
     **/
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public LesseeCompanyQuery setCompanyName(String companyName) {
        this.companyName = companyName;
        this.params.put("companyName", companyName);
        return this;
    }

    /**
     * 单元编号
     **/
    private String unitNumber;

    public String getUnitNumber() {
        return unitNumber;
    }

    public LesseeCompanyQuery setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
        this.params.put("unitNumber", unitNumber);
        return this;
    }

    /**
     * 创建时间
     **/
    private Date addTimeStart;

    public Date getAddTimeStart() {
        return addTimeStart;
    }

    public LesseeCompanyQuery setAddTimeStart(Date addTime) {
        this.addTimeStart = addTime;
        this.params.put("addTimeStart", addTime);
        return this;
    }

    private Date addTimeEnd;

    public Date getAddTimeEnd() {
        return addTimeEnd;
    }

    public LesseeCompanyQuery setAddTimeEnd(Date addTime) {
        this.addTimeEnd = addTime;
        this.params.put("addTimeEnd", addTime);
        return this;
    }

    private Date addTimeEqual;

    public Date getAddTimeEqual() {
        return addTimeEqual;
    }

    public LesseeCompanyQuery setAddTimeEqual(Date addTime) {
        this.addTimeEqual = addTime;
        this.params.put("addTimeEqual", addTime);
        return this;
    }

    /**
     * 修改时间
     **/
    private Date updTimeStart;

    public Date getUpdTimeStart() {
        return updTimeStart;
    }

    public LesseeCompanyQuery setUpdTimeStart(Date updTime) {
        this.updTimeStart = updTime;
        this.params.put("updTimeStart", updTime);
        return this;
    }

    private Date updTimeEnd;

    public Date getUpdTimeEnd() {
        return updTimeEnd;
    }

    public LesseeCompanyQuery setUpdTimeEnd(Date updTime) {
        this.updTimeEnd = updTime;
        this.params.put("updTimeEnd", updTime);
        return this;
    }

    private Date updTimeEqual;

    public Date getUpdTimeEqual() {
        return updTimeEqual;
    }

    public LesseeCompanyQuery setUpdTimeEqual(Date updTime) {
        this.updTimeEqual = updTime;
        this.params.put("updTimeEqual", updTime);
        return this;
    }
    /**==============================批量查询时的Order条件顺序设置==================================**/
    /**
     * 设置排序按属性：自增主键
     *
     * @param isAsc 是否升序，否则为降序
     */
    public LesseeCompanyQuery orderbyId(boolean isAsc) {
        orderFields.add(new OrderField("id", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：企业名称
     *
     * @param isAsc 是否升序，否则为降序
     */
    public LesseeCompanyQuery orderbyCompanyName(boolean isAsc) {
        orderFields.add(new OrderField("company_name", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：单元编号
     *
     * @param isAsc 是否升序，否则为降序
     */
    public LesseeCompanyQuery orderbyUnitNumber(boolean isAsc) {
        orderFields.add(new OrderField("unit_number", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：创建时间
     *
     * @param isAsc 是否升序，否则为降序
     */
    public LesseeCompanyQuery orderbyAddTime(boolean isAsc) {
        orderFields.add(new OrderField("add_time", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：修改时间
     *
     * @param isAsc 是否升序，否则为降序
     */
    public LesseeCompanyQuery orderbyUpdTime(boolean isAsc) {
        orderFields.add(new OrderField("upd_time", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    @Override
    public Map<String, String> getFieldSet() {
        super.getFieldSet();
        fieldMap.put("id", "id");
        fieldMap.put("company_name", "companyName");
        fieldMap.put("unit_number", "unitNumber");
        fieldMap.put("add_time", "addTime");
        fieldMap.put("upd_time", "updTime");
        return fieldMap;
    }
}
