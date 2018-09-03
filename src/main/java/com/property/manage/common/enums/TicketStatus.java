package com.property.manage.common.enums;


public enum TicketStatus {

    UN_TICKET(0, "未开票"),

    TICKET(1, "已开票");

    private Integer key;

    private String value;

    TicketStatus(Integer key, String value) {
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
    public static TicketStatus find(Integer key) {
        // 循环处理
        for (TicketStatus type : TicketStatus.values()) {
            // 匹配值
            if (type.getKey().equals(key)) {
                // 返回类型
                return type;
            }
        }
        // 未找到
        return null;
    }

    public static TicketStatus findByValue(String value) {
        // 循环处理
        for (TicketStatus type : TicketStatus.values()) {
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
