/*
 * ExcelHead.java
 * Copyright by ddy
 * Description：
 * Modified By：Administrator
 * Modified Time：2015年9月8日
 * Modified Number：
 * Modified Contents：
 */

package com.property.manage.base.excel.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.property.manage.base.excel.enums.CellStatus;
import com.property.manage.base.excel.model.CellData;
import com.property.manage.base.excel.model.Header;
import com.property.manage.base.model.constants.CommonConstants;
import com.property.manage.base.model.exception.ParameterException;
import com.property.manage.base.model.utils.CastUtils;
import com.property.manage.base.model.utils.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Map;


/**
 * Excl导出表格头部类
 *
 * @author guanhj
 * @version 2016年7月12日
 * @see ExcelUtils
 */
public class ExcelUtils {

    private static final transient Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * EXCEL导出头部字段名称
     */
    public static final String REQUIRED = "(必填)";

    /**
     * 提前检查
     *
     * @param cellDatas
     * @throws ParameterException
     */
    public static void cellDataCheck(Map<String, CellData> cellDatas, String message) throws ParameterException {
        // 转换列表
        List<CellData> cellDataList = ListUtils.map2List(cellDatas);
        // 循环处理
        for (CellData cellData : cellDataList) {
            // 有异常情况
            if (CellStatus.ERROR.equals(cellData.getStatus()) || CellStatus.WARNING.equals(cellData.getStatus())) {
                // 抛出异常
                throw new ParameterException(StringUtils.isNotBlank(message) ? message : "检查有异常,终止本条数据导入");
            }
        }
    }

    /**
     * Description: 转换Excel表头<br>
     *
     * @param heads
     * @return
     */
    public static List<Header> excelHeader(String[] heads) {
        // 表头集合
        List<Header> headerLst = Lists.newArrayList();
        // 表头个数
        int len = heads.length;
        // 单个表头Map
        Header h = null;
        // 循环处理
        for (int i = 0; i < len; i++) {
            // 当前表头
            String head = heads[i];
            // 根据冒号分隔
            String[] headVector = head.split(CommonConstants.STR_COLON);
            // 如果格式不正确则不认为是表头
            if (headVector.length == 2) {
                // 实例化表头Map
                h = new Header();
                // 设定Field
                h.setField(headVector[1]);
                // 设定Field名称
                h.setName(headVector[0]);
                // 写入头部集合
                headerLst.add(h);
            }
        }
        return headerLst;
    }

    /**
     * 转换表头
     *
     * @param headers
     * @return
     */
    public static List<Header> convHeader(List<? extends Header> headers) {
        // 表头列表
        List<Header> heads = Lists.newArrayList();
        // 循环处理
        for (Header header : headers) {
            // 实例化对象
            Header head = new Header();
            // 拷贝数据
            BeanUtils.copyProperties(header, head);
            // 添加元素
            heads.add(head);
        }
        // 返回列表
        return heads;
    }

    /**
     * 转换Excel单元格数据
     *
     * @param datas
     * @return
     */
    public static Map<String, Object> convCellDatas(Map<String, CellData> datas) {
        // 转换后的数据集合
        Map<String, Object> convDatas = Maps.newHashMap();
        // 遍历MAP
        for (Map.Entry<String, CellData> entry : datas.entrySet()) {
            // 取得单元格对象
            CellData cellData = entry.getValue();
            // 异常处理
            if (null != cellData) {
                // 填充数据
                convDatas.put(entry.getKey(), cellData.getValue());
            }
        }
        // 返回集合
        return convDatas;
    }

    /**
     * 设置单元格错误信息
     *
     * @param cellDatas
     * @param field
     * @param message
     */
    public static void makeCellDataError(Map<String, CellData> cellDatas, String field, String message) {
        // 取得客户名称单元格
        CellData cellData = cellDatas.get(field);
        // 设置单元格错误信息
        makeCellDataError(cellData, message);
    }

    /**
     * 设置单元格错误信息
     *
     * @param cellData
     * @param message
     */
    public static void makeCellDataError(CellData cellData, String message) {
        // 异常处理
        if (null != cellData) {
            // 设定错误状态
            cellData.setStatus(CellStatus.ERROR);
            // 设定错误信息
            cellData.setComment(message);
        }
    }

    /**
     * 转换JSON格式
     *
     * @param json
     * @return
     */
    public static List<Map<String, CellData>> makeJsonToListCellDatas(String json) {
        // 异常处理
        if (StringUtils.isBlank(json)) {
            // 中断流程
            return null;
        }
        try {
            // 取得错误数据
            return JSON.parseObject(json, new TypeReference<List<Map<String, CellData>>>() {
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        // 返回null
        return null;
    }

    /**
     * 转换map object到map CellData
     *
     * @param datas
     * @return
     */
    public static List<Map<String, CellData>> makeListCellDatas(List<Map<String, Object>> datas) {
        // 实例化列表
        List<Map<String, CellData>> cellDatas = Lists.newArrayList();
        // 异常处理
        if (null == datas || datas.isEmpty()) {
            // 中断流程
            return cellDatas;
        }
        // 循环处理
        for (Map<String, Object> data : datas) {
            // 转换Bean到map
            cellDatas.add(makeCellDatas(data));
        }
        return cellDatas;
    }

    /**
     * 转换map object到map CellData
     *
     * @param data
     * @return
     */
    public static Map<String, CellData> makeCellDatas(Map<String, Object> data) {
        // 实例化集合
        Map<String, CellData> cellData = Maps.newHashMap();
        // 异常处理
        if (null == data || data.isEmpty()) {
            // 中断流程
            return cellData;
        }
        // 循环处理
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            // 字段标题
            CellData cell = new CellData();
            // 设定值
            cell.setValue(entry.getValue());
            // 设定元素
            cellData.put(entry.getKey(), cell);
        }
        return cellData;
    }

    /**
     * 转换BeanList到MapList
     *
     * @param beans
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, CellData>> makeBeanToListCellDatas(List<T> beans) {
        // 实例化列表
        List<Map<String, CellData>> cellDatas = Lists.newArrayList();
        // 异常处理
        if (null == beans || beans.isEmpty()) {
            // 中断流程
            return cellDatas;
        }
        // 循环处理
        for (T bean : beans) {
            // 转换Bean到map
            cellDatas.add(makeBeanToCellDatas(bean));
        }
        return cellDatas;
    }

    /**
     * 转换Bean到map
     *
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> Map<String, CellData> makeBeanToCellDatas(T bean) {
        // 实例化集合
        Map<String, CellData> cellData = Maps.newHashMap();
        // 将Bean转换为map
        Map<String, Object> datas = CastUtils.castBeanToMapWithOutJsonField(bean, null);
        // 异常处理
        if (null == datas || datas.isEmpty()) {
            // 中断流程
            return cellData;
        }
        // 循环处理
        for (Map.Entry<String, Object> entry : datas.entrySet()) {
            // 字段标题
            CellData cell = new CellData();
            // 设定值
            cell.setValue(entry.getValue());
            // 设定元素
            cellData.put(entry.getKey(), cell);
        }
        return cellData;
    }
}
