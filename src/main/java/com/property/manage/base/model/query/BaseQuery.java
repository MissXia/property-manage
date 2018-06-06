package com.property.manage.base.model.query;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.property.manage.base.model.model.OrderField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class BaseQuery implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -6434626019139154584L;

    /**
     * 日志对象
     */
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 参数列表
     */
    protected Map<String, Object> params = Maps.newHashMap();

    /**
     * 排序列表字段
     */
    protected List<OrderField> orderFields = Lists.newArrayList();

    /**
     * 字段列表字符串
     */
    protected String fields;

    /**
     * 提供自定义字段使用
     */
    protected Map<String, String> fieldMap;

    /**
     * 主键列表
     */
    protected List<String> keys;

    /**
     * 每页条数
     */
    protected Integer pageSize = 10;

    /**
     * 起始行
     */
    protected Integer startRow;

    /**
     * 结束行(闭合)
     */
    protected Integer endRow;

    /**
     * 当前页数
     */
    protected Integer page = 1;

    /**
     * 分表ID
     */
    private String tableId;

    /**
     * 企业id
     */
    private String corpId;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getCorpId() {
        return corpId;
    }

    public BaseQuery setCorpId(String corpId) {
        this.corpId = corpId;
        return this;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public BaseQuery setStartRow(Integer startRow) {
        this.startRow = startRow;
        return this;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public BaseQuery setEndRow(Integer endRow) {
        this.endRow = endRow;
        return this;
    }

    public BaseQuery setPage(Integer page) {
        if (page < 0){page = 1;}
        this.page = page;
        this.startRow = (page - 1) * this.pageSize;
        this.endRow = this.startRow + this.pageSize - 1;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public BaseQuery setPageSize(Integer pageSize) {
        if (pageSize < 1){pageSize = 10;}
        this.pageSize = pageSize;
        this.startRow = (page - 1) * this.pageSize;
        this.endRow = this.startRow + this.pageSize - 1;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    /**
     * 构造参数
     *
     * @return
     */
    public Map<String, Object> getParams() throws SQLException {
        // 分表ID
        this.params.put("tableId", this.getTableId());
        // 企业id
        this.params.put("corpId", this.getCorpId());
        // 当前页码
        this.params.put("page", this.getPage());
        // 每页显示条数
        this.params.put("pageSize", this.getPageSize());
        // 开始行数
        this.params.put("startRow", this.getStartRow());
        // 结束行数
        this.params.put("endRow", this.getEndRow());
        // 排序字段
        this.params.put("orderFields", this.getOrderFields());
        // 字段列表
        this.params.put("fields", this.getFields());
        // 返回参数
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public List<OrderField> getOrderFields() {
        return orderFields;
    }

    public void setOrderFields(List<OrderField> orderFields) {
        this.orderFields = orderFields;
    }

    public Map<String, String> getFieldSet() {
        if (fieldMap == null) {
            fieldMap = Maps.newHashMap();
        }
        return fieldMap;
    }

    public String getFields() {
        return this.fields;
    }

    public void setFields(String fields) {
        this.fields = makeFieldString(fields, getFieldSet());
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
        this.params.put("keys", keys);
    }

    /**
     * 生成字段字符串
     *
     * @param fields
     * @param fieldMap
     * @return
     */
    protected String makeFieldString(String fields, Map<String, String> fieldMap) {
        // 按空格分割
        Splitter splitter = Splitter.on(",");
        // 取得分割后的单词
        List<String> fieldList = splitter.omitEmptyStrings().splitToList(fields);
        // 拼接字符串
        StringBuilder buffer = new StringBuilder();
        // 循环处理
        for (String field : fieldList) {
            // 如包含字段
            if (fieldMap.containsKey(field)) {
                // 添加as字符串
                buffer.append(field).append(" as ").append(fieldMap.get(field)).append(" ,");
            }
            // 如果包含字段
            if (fieldMap.containsKey("`" + field + "`")) {
                // 添加as字符串
                buffer.append("`").append(field).append("`").append(" as ").append(fieldMap.get(field)).append(" ,");
            }
        }
        // 如果存在字段
        if (buffer.length() != 0) {
            // 返回字段列表字符串
            return buffer.substring(0, buffer.length() - 1);
        }
        // 没有一个参数可能会报错
        return "1";
    }
}