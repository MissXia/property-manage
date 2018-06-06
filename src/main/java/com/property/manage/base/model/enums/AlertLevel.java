package com.property.manage.base.model.enums;


public enum AlertLevel {

    SUCCESS(0, "成功"),

    FAILD(1, "失败"),

    WARNING(2, "警告"),

    INFO(3, "提示");

    private Integer key;

    private String detail;

    AlertLevel(Integer key, String detail) {
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