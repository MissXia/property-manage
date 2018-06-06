package com.property.manage.base.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties({"splitTableName", "splitDBName", "tableId"})
public class BasePojo implements Serializable {

    private static final long serialVersionUID = -1303125348820335896L;

    @JsonIgnore
    private String splitTableName;

    @JsonIgnore
    private String splitDBName;

    @JsonIgnore
    private String tableId;

    public String getSplitTableName() {
        return splitTableName;
    }

    public void setSplitTableName(String splitTableName) {
        this.splitTableName = splitTableName;
    }

    public String getSplitDBName() {
        return splitDBName;
    }

    public void setSplitDBName(String splitDBName) {
        this.splitDBName = splitDBName;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
