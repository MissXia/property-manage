package com.property.manage.base.model.enums;


public enum Comparison {

    CONTAIN(0, "包含"),

    NOT_CONTAIN(1, "不包含"),

    EQUAL(2, "等于"),

    NOT_EQUAL(3, "不等于"),

    MORE(4, "大于"),

    MORE_EQUAL(5, "大于等于"),

    LESS(6, "小于"),

    LESS_EQUAL(7, "小于等于"),

    NULL(8, "空"),

    NOT_NULL(9, "不为空"),

    START(10, "开始于"),

    END(11, "结束于"),

    IN_ARRAY(12, "在数组内"),

    REVERSE_IN_ARRAY(13, "在参数内");

    private Integer key;

    private String detail;

    Comparison(Integer key, String detail) {
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

    public static Comparison find(Integer key) {
        // 循环处理
        for (Comparison type : Comparison.values()) {
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