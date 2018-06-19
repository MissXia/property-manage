/*
 * ExcelHead.java
 * Copyright by ddy
 * Description：
 * Modified By：Administrator
 * Modified Time：2015年9月8日
 * Modified Number：
 * Modified Contents：
 */

package com.property.manage.base.excel.enums;


public enum CellStatus {

    TIPS(0, "提示"),

    WARNING(1, "警告"),

    ERROR(2, "错误");

    private Integer key;

    private String detail;

    CellStatus(Integer key, String detail) {
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

    public static CellStatus find(Integer key) {
        // 循环处理
        for (CellStatus status : CellStatus.values()) {
            // 匹配值
            if (status.getKey().equals(key)) {
                // 返回类型
                return status;
            }
        }
        // 未找到
        return null;
    }
}