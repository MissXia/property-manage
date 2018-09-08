package com.property.manage.common.query;

import com.property.manage.base.model.enums.SortDirection;
import com.property.manage.base.model.model.OrderField;

import java.util.Map;


/**
 * @author 管辉俊
 */
public class LesseeCompanyViewQuery extends LesseeCompanyQuery {

    /**==============================批量查询、更新、删除时的Where条件设置==================================**/
    /**
     * 单元编号
     **/
    private String unitNumber;

    public String getUnitNumber() {
        return unitNumber;
    }

    public LesseeCompanyViewQuery setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
        this.params.put("unitNumber", unitNumber);
        return this;
    }

    /**==============================批量查询时的Order条件顺序设置==================================**/
    /**
     * 设置排序按属性：单元编号
     *
     * @param isAsc 是否升序，否则为降序
     */
    public LesseeCompanyViewQuery orderbyUnitNumber(boolean isAsc) {
        orderFields.add(new OrderField("unit_number", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    @Override
    public Map<String, String> getFieldSet() {
        fieldMap = super.getFieldSet();
        fieldMap.put("unit_number", "unitNumber");
        return fieldMap;
    }
}
