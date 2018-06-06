package com.property.manage.base.model.enums;


import java.math.BigDecimal;
import java.util.Date;

public enum MySqlDataType {

    TINYINT("TINYINT", "0", Integer.class),

    SMALLINT("SMALLINT", "0", Integer.class),

    MEDIUMINT("MEDIUMINT", "0", Integer.class),

    INTEGER("INTEGER", "0", Integer.class),

    BIGINT("BIGINT", "0", Long.class),

    FLOAT("FLOAT", "0", Float.class),

    DOUBLE("DOUBLE", "0", Double.class),

    DECIMAL("DECIMAL", "0", BigDecimal.class),

    DATE("DATE", "", Date.class),

    TIME("TIME", "", Date.class),

    YEAR("YEAR", "", String.class),

    DATETIME("DATETIME", "", Date.class),

    TIMESTAMP("TIMESTAMP", "", Date.class),

    CHAR("CHAR", "", String.class),

    VARCHAR("VARCHAR", "", String.class),

    TINYBLOB("TINYBLOB", "", String.class),

    TINYTEXT("TINYTEXT", "", String.class),

    BLOB("BLOB", "", String.class),

    TEXT("TEXT", "", String.class),

    MEDIUMBLOB("MEDIUMBLOB", "", String.class),

    MEDIUMTEXT("MEDIUMTEXT", "", String.class),

    LONGBLOB("LONGBLOB", "", String.class),

    LONGTEXT("LONGTEXT", "", String.class);

    private String value;

    private String defaultValue;

    private Class jdbcType;

    MySqlDataType(String value, String defaultValue, Class jdbcType) {
        this.value = value;
        this.defaultValue = defaultValue;
        this.jdbcType = jdbcType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Class getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(Class jdbcType) {
        this.jdbcType = jdbcType;
    }
}
