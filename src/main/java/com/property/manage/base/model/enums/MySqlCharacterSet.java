package com.property.manage.base.model.enums;


public enum MySqlCharacterSet {

    utf8mb4("utf8mb4");

    private String value;

    MySqlCharacterSet(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
