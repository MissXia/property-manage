package com.property.manage.common.enums;


public enum PayStatus {

    UN_PAY(0, "未缴费"),

    PAY(1, "已缴费");

    private Integer key;

    private String value;

    PayStatus(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 反向查找
     *
     * @param key
     * @return
     */
    public static PayStatus find(Integer key) {
        // 循环处理
        for (PayStatus type : PayStatus.values()) {
            // 匹配值
            if (type.getKey().equals(key)) {
                // 返回类型
                return type;
            }
        }
        // 未找到
        return null;
    }

    public static PayStatus findByValue(String value) {
        // 循环处理
        for (PayStatus type : PayStatus.values()) {
            // 匹配值
            if (type.getValue().equals(value)) {
                // 返回类型
                return type;
            }
        }
        // 未找到
        return null;
    }
}
