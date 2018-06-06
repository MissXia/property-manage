package com.property.manage.base.model.model;


import com.property.manage.base.model.enums.SortDirection;

import java.io.Serializable;

public class OrderField implements Serializable {

    private static final long serialVersionUID = 5907686226047745734L;

    private String alias;

    private Integer fieldType;

    private String fieldName;

    private SortDirection order;

    public OrderField() {
    }

    public OrderField(String fieldName, SortDirection order) {
        this.fieldName = fieldName;
        this.order = order;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public SortDirection getOrder() {
        return order;
    }

    public void setOrder(SortDirection order) {
        this.order = order;
    }
}