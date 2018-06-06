package com.property.manage.base.model.enums;


public enum SortDirection {

    ASC("asc", "正序"),

    DESC("desc", "倒序");

    private String key;

    private String detail;

    SortDirection(String key, String detail) {
        this.key = key;
        this.detail = detail;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
