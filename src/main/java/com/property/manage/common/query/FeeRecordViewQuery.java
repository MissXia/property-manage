package com.property.manage.common.query;

import com.property.manage.base.model.enums.SortDirection;
import com.property.manage.base.model.model.OrderField;

import java.util.Map;

/**
 * @author 管辉俊
 */
public class FeeRecordViewQuery extends FeeRecordQuery {

    /**==============================批量查询、更新、删除时的Where条件设置==================================**/
    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public FeeRecordViewQuery setItemName(String itemName) {
        this.itemName = itemName;
        this.params.put("itemName", itemName);
        return this;
    }

    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public FeeRecordViewQuery setCompanyName(String companyName) {
        this.companyName = companyName;
        this.params.put("companyName", companyName);
        return this;
    }

    private String unitNumber;

    public String getUnitNumber() {
        return unitNumber;
    }

    public FeeRecordViewQuery setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
        this.params.put("unitNumber", unitNumber);
        return this;
    }

    /**==============================批量查询时的Order条件顺序设置==================================**/

    public FeeRecordViewQuery orderbyItemName(boolean isAsc) {
        orderFields.add(new OrderField("item_name", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    public FeeRecordViewQuery orderbyCompanyName(boolean isAsc) {
        orderFields.add(new OrderField("company_name", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    public FeeRecordViewQuery orderbyUnitNumber(boolean isAsc) {
        orderFields.add(new OrderField("unit_number", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    @Override
    public Map<String, String> getFieldSet() {
        fieldMap = super.getFieldSet();
        fieldMap.put("item_name", "itemName");
        fieldMap.put("company_name", "companyName");
        fieldMap.put("unit_number", "unitNumber");
        return fieldMap;
    }
}
