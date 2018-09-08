package com.property.manage.common.query;

import com.property.manage.base.model.enums.SortDirection;
import com.property.manage.base.model.model.OrderField;

import java.util.Map;


/**
 * @author 管辉俊
 */
public class LesseeCompanyUnitViewQuery extends LesseeCompanyUnitQuery {

    /**==============================批量查询、更新、删除时的Where条件设置==================================**/
    /**
     * 企业名称
     **/
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public LesseeCompanyUnitViewQuery setCompanyName(String companyName) {
        this.companyName = companyName;
        this.params.put("companyName", companyName);
        return this;
    }
    /**==============================批量查询时的Order条件顺序设置==================================**/
    /**
     * 设置排序按属性：企业名称
     *
     * @param isAsc 是否升序，否则为降序
     */
    public LesseeCompanyUnitViewQuery orderbyCompanyName(boolean isAsc) {
        orderFields.add(new OrderField("company_name", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    @Override
    public Map<String, String> getFieldSet() {
        fieldMap = super.getFieldSet();
        fieldMap.put("company_name", "companyName");
        return fieldMap;
    }
}
