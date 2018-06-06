package com.property.manage.base.model.enums;


public enum DateUnit {
    // 0、分 1、时 2、天 3、月
    MINUTE(0),

    HOUR(1),

    DAY(2),

    MONTH(3);

    private Integer value;

    DateUnit(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * 反向查找
     *
     * @param value
     * @return
     */
    public static DateUnit find(Integer value) {
        // 循环处理
        for (DateUnit type : DateUnit.values()) {
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
