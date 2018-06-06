package com.property.manage.base.model.enums;


public enum Connector {

    AND("AND", "且"),

    OR("OR", "或");

    private String key;

    private String detail;

    Connector(String key, String detail) {
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

    public static Connector find(String key) {
        // 循环处理
        for (Connector type : Connector.values()) {
            // 匹配值
            if (type.getKey().equals(key)) {
                // 返回类型
                return type;
            }
        }
        // 未找到
        return null;
    }
}