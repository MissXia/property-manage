package com.property.manage.base.excel.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.property.manage.base.excel.model.CellData;
import com.property.manage.base.excel.model.Header;
import com.property.manage.base.excel.service.ReadExcel;
import com.property.manage.base.model.constants.CommonConstants;
import com.property.manage.base.model.exception.ParameterException;
import com.property.manage.base.model.utils.AppDateUtils;
import com.property.manage.base.model.utils.CastUtils;
import com.property.manage.base.model.utils.DoubleUtils;
import com.property.manage.base.model.utils.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReadUtils {

    private static Logger logger = LoggerFactory.getLogger(ReadUtils.class);

    /**
     * 导入最大条数
     */
    private final static int MAX_RECORD_COUNT = 10000;

    /**
     * 生成Excel数据
     *
     * @param file
     * @param headerList
     * @return
     * @throws ParameterException
     */
    public static List<Map<String, CellData>> makeExcelCellDatas(MultipartFile file, List<Header> headerList) throws ParameterException {
        // 异常处理
        if (null == headerList || headerList.isEmpty()) {
            // 中断流程
            throw new ParameterException("模板表头列表为空!");
        }
        // 读取Excel数据
        List<List<CellData>> datas = readExcelDatas(file, 0, headerList);
        // 异常处理
        if (null == datas || datas.isEmpty()) {
            // 中断流程
            throw new ParameterException("读取数据失败!");
        }
        // 异常处理
        if (datas.size() == 1) {
            // 中断流程
            throw new ParameterException("需要导入的数据条数为0!");
        }
        // 最大条数判断
        if (datas.size() > MAX_RECORD_COUNT + 1) {
            // 中断流程
            throw new ParameterException("每次最多导入" + MAX_RECORD_COUNT + "条数据!");
        }
        // 取得表头数据
        List<CellData> headDatas = datas.get(0);
        // 检查表头是否一致
        if (!checkExcelHead(headerList, headDatas)) {
            // 中断流程
            throw new ParameterException("模板有变更,请重新下载模板!");
        }
        // 取得表体数据
        List<List<CellData>> bodyDatas = datas.subList(1, datas.size());
        // 生成Excel数据
        return createExcelDatas(bodyDatas, headerList);
    }

    /**
     * 检查表头是否一致
     *
     * @param headers
     * @param headDatas
     * @return
     */
    private static boolean checkExcelHead(List<Header> headers, List<CellData> headDatas) {
        // 匹配到的个数
        int matched = 0;
        // 循环处理
        for (Header header : headers) {
            // 循环处理
            for (CellData headData : headDatas) {
                // 取得单元格的值
                String cellValue = null == headData.getValue() ? CommonConstants.STR_BLANK : headData.getValue().toString();
                // 如果中文匹配上
                if (cellValue.equals(header.getName())) {
                    // 设定列索引
                    header.setColumnIndex(headData.getColumnIndex());
                    // 累计个数
                    matched++;
                    // 跳出当前循环
                    break;
                }
            }
        }
        // 判断是否全部匹配到
        return matched == headers.size();
    }

    /**
     * 读取Excel数据
     *
     * @param file
     * @param sheetIndex
     * @param heads
     * @return
     */
    private static List<List<CellData>> readExcelDatas(MultipartFile file, Integer sheetIndex, List<Header> heads) {
        // 统计开始时间
        Long startTime = System.currentTimeMillis();
        logger.info("==取得Excel数据==开始读取文件");
        // 读取数据(可以为空)
        List<List<CellData>> datas = ReadExcel.readExcel(file, sheetIndex, 0, heads.size(), false);
        logger.info("==取得Excel数据==读取文件耗时[{}]", System.currentTimeMillis() - startTime);
        // 异常处理
        if (null == datas || datas.isEmpty()) {
            // 中断流程
            return datas;
        }
        logger.info("==取得Excel数据==取得数据[{}]条", datas.size());
        // 返回Excel数据
        return datas;
    }

    /**
     * 取得Excel数据
     *
     * @param datas
     * @param headerList
     * @return
     */
    private static List<Map<String, CellData>> createExcelDatas(List<List<CellData>> datas, List<Header> headerList) {
        // 数据列表
        List<Map<String, CellData>> records = Lists.newArrayList();
        // 统计开始时间
        Long startTime = System.currentTimeMillis();
        // 按列索引生成MAP
        Map<Integer, Header> headers = ListUtils.list2Map(headerList, "columnIndex");
        // 循环处理
        for (List<CellData> data : datas) {
            // 取得单条账房数据
            Map<String, CellData> record = createExcelData(data, headers);
            // 添加元素
            records.add(record);
        }
        logger.info("==取得Excel数据==生成待填充数据耗时[{}]", System.currentTimeMillis() - startTime);
        return records;
    }

    /**
     * 取得单条Excel数据
     *
     * @param data
     * @param headers
     * @return
     */
    private static Map<String, CellData> createExcelData(List<CellData> data, Map<Integer, Header> headers) {
        // 实例化对象
        Map<String, CellData> record = Maps.newHashMap();
        // 循环处理
        for (CellData cellData : data) {
            // 按列索引取得表头
            Header header = headers.get(cellData.getColumnIndex());
            // 如果有取得表头
            if (null != header) {
                // 设定数据
                record.put(header.getField(), cellData);
            }
        }
        // 返回对象
        return record;
    }

    /**
     * 生成数值值
     *
     * @param value
     * @return
     */
    private static BigDecimal makeDecimalValue(Object value) {
        if (null == value) {
            return BigDecimal.ZERO;
        }
        String s = value.toString();
        Double d = CastUtils.castToDouble(s);
        if (null == d) {
            return BigDecimal.ZERO;
        }
        int dot = DoubleUtils.getNumberDecimalDigits(s);
        return (new BigDecimal(d)).setScale(dot, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 生成字符串值
     *
     * @param value
     * @return
     */
    private static String makeStringValue(Object value) {
        if (null == value) {
            return "";
        }
        return (value.toString());
    }

    /**
     * 生成日期值
     *
     * @param value
     * @return
     */
    private static Date makeDateValue(Object value) {
        if (null == value) {
            return null;
        }
        if (value instanceof Date) {
            return (Date) value;
        }
        return AppDateUtils.parseDate(value.toString());
    }
}
