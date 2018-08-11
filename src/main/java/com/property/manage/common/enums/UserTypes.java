package com.property.manage.common.enums;


public enum UserTypes {

    FAILD(-1, "认证失败"),

    UNKNOW(0, "未知"),

    NORMAL(1, "普通用户"),

    OPEARTOR(7, "普通员工"),

    ADMIN(8, "管理员"),

    SUPER_ADMIN(9, "超级管理员");

    private Integer key;

    private String value;

    UserTypes(Integer key, String value) {
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
    public static UserTypes find(Integer key) {
        // 循环处理
        for (UserTypes type : UserTypes.values()) {
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
