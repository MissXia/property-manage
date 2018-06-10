package com.property.manage.common.query;

import com.property.manage.base.model.enums.SortDirection;
import com.property.manage.base.model.model.OrderField;
import com.property.manage.base.model.query.BaseQuery;

import java.util.Date;
import java.util.Map;

/**
 * @author 管辉俊
 */
public class FeeItemQuery extends BaseQuery {

    /**==============================批量查询、更新、删除时的Where条件设置==================================**/
    /**
     * 自增主键
     **/
    private Long id;

    public Long getId() {
        return id;
    }

    public FeeItemQuery setId(Long id) {
        this.id = id;
        this.params.put("id" , id);
        return this;
    }

    /**
     * 项目名称
     **/
    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public FeeItemQuery setItemName(String itemName) {
        this.itemName = itemName;
        this.params.put("itemName" , itemName);
        return this;
    }

    /**
     * 创建时间
     **/
    private Date addTimeStart;

    public Date getAddTimeStart() {
        return addTimeStart;
    }

    public FeeItemQuery setAddTimeStart(Date addTime) {
        this.addTimeStart = addTime;
        this.params.put("addTimeStart" , addTime);
        return this;
    }

    private Date addTimeEnd;

    public Date getAddTimeEnd() {
        return addTimeEnd;
    }

    public FeeItemQuery setAddTimeEnd(Date addTime) {
        this.addTimeEnd = addTime;
        this.params.put("addTimeEnd" , addTime);
        return this;
    }

    private Date addTimeEqual;

    public Date getAddTimeEqual() {
        return addTimeEqual;
    }

    public FeeItemQuery setAddTimeEqual(Date addTime) {
        this.addTimeEqual = addTime;
        this.params.put("addTimeEqual" , addTime);
        return this;
    }

    /**
     * 修改时间
     **/
    private Date updTimeStart;

    public Date getUpdTimeStart() {
        return updTimeStart;
    }

    public FeeItemQuery setUpdTimeStart(Date updTime) {
        this.updTimeStart = updTime;
        this.params.put("updTimeStart" , updTime);
        return this;
    }

    private Date updTimeEnd;

    public Date getUpdTimeEnd() {
        return updTimeEnd;
    }

    public FeeItemQuery setUpdTimeEnd(Date updTime) {
        this.updTimeEnd = updTime;
        this.params.put("updTimeEnd" , updTime);
        return this;
    }

    private Date updTimeEqual;

    public Date getUpdTimeEqual() {
        return updTimeEqual;
    }

    public FeeItemQuery setUpdTimeEqual(Date updTime) {
        this.updTimeEqual = updTime;
        this.params.put("updTimeEqual" , updTime);
        return this;
    }
    /**==============================批量查询时的Order条件顺序设置==================================**/
    /**
     * 设置排序按属性：自增主键
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeItemQuery orderbyId(boolean isAsc) {
        orderFields.add(new OrderField("id" , isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：项目名称
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeItemQuery orderbyItemName(boolean isAsc) {
        orderFields.add(new OrderField("item_name" , isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：创建时间
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeItemQuery orderbyAddTime(boolean isAsc) {
        orderFields.add(new OrderField("add_time" , isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：修改时间
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeItemQuery orderbyUpdTime(boolean isAsc) {
        orderFields.add(new OrderField("upd_time" , isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    @Override
    public Map<String, String> getFieldSet() {
        super.getFieldSet();
        fieldMap.put("id" , "id");
        fieldMap.put("item_name" , "itemName");
        fieldMap.put("add_time" , "addTime");
        fieldMap.put("upd_time" , "updTime");
        return fieldMap;
    }
}
