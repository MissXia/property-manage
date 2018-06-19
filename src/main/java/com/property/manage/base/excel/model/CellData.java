/*
 * ExcelHead.java
 * Copyright by ddy
 * Description：
 * Modified By：Administrator
 * Modified Time：2015年9月8日
 * Modified Number：
 * Modified Contents：
 */

package com.property.manage.base.excel.model;


import com.property.manage.base.excel.enums.CellStatus;
import org.apache.poi.ss.usermodel.CellType;

public class CellData {

    private Integer rowIndex;

    private Integer columnIndex;

    private CellStatus status;

    private String comment;

    private Object value;

    private CellType cellType;

    private Boolean dateCell = false;

    public CellData() {
    }

    public CellData(CellStatus status, String comment, Object value) {
        this.status = status;
        this.comment = comment;
        this.value = value;
    }

    public CellData(Integer rowIndex, Integer columnIndex, CellStatus status, String comment, Object value) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.status = status;
        this.comment = comment;
        this.value = value;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Integer getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(Integer columnIndex) {
        this.columnIndex = columnIndex;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public Boolean getDateCell() {
        return dateCell;
    }

    public void setDateCell(Boolean dateCell) {
        this.dateCell = dateCell;
    }
}