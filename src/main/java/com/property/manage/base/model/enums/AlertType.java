package com.property.manage.base.model.enums;


public enum AlertType {

    CONFIRM(0, "确认框"),

    DAILOG(1, "对话框");

    private Integer key;

    private String detail;

    AlertType(Integer key, String detail) {
        this.key = key;
        this.detail = detail;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}