/*
 * ExportExcel.java
 * Copyright by ddy
 * Description：
 * Modified By：Administrator
 * Modified Time：2015年9月7日
 * Modified Number：
 * Modified Contents：
 */

package com.property.manage.base.excel.service;

import com.google.common.collect.Lists;
import com.property.manage.base.excel.model.CellData;
import com.property.manage.base.model.utils.CastUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Pattern;

/**
 * POI共通类
 *
 * @author guanhj
 * @version 2015年9月7日
 * @see ReadExcel
 */
public class ReadExcel {

    private static Logger logger = LoggerFactory.getLogger(ReadExcel.class);

    private final static String EXCEL_2003 = ".xls";

    private static DecimalFormat str_df = new DecimalFormat("0");

    private static DecimalFormat dou_df = new DecimalFormat("####################.########");

    private final static String DATE_FORMAT_REG = "[YyMmDdHhSs\\W]+";

    /**
     * 读取Excel
     *
     * @param excelFile
     * @param sheetIndex
     * @param rowIndex
     * @param colsCount
     * @return
     */
    public static List<List<CellData>> readExcel(MultipartFile excelFile, Integer sheetIndex, Integer rowIndex, Integer colsCount) {
        // 生成Excel文件
        return readExcel(excelFile, sheetIndex, rowIndex, colsCount, true);
    }

    /**
     * 读取Excel
     *
     * @param excelFile
     * @param sheetIndex
     * @param rowIndex
     * @param colsCount
     * @return
     */
    public static List<List<CellData>> readExcel(MultipartFile excelFile, Integer sheetIndex, Integer rowIndex, Integer colsCount, boolean notNull) {
        // 参数异常判断
        if (null == excelFile || null == sheetIndex || sheetIndex < 0) {
            // 中断流程
            return null;
        }
        try {
            // 取得读取流
            InputStream is = excelFile.getInputStream();
            // 工作簿
            Workbook workbook = null;
            // 如果是2003版本的Excel
            if (excelFile.getOriginalFilename().endsWith(EXCEL_2003)) {
                // 2003版本
                workbook = new HSSFWorkbook(is);
            } else {
                // 2007版本
                workbook = new XSSFWorkbook(is);
            }
            // 读取
            return readSheets(workbook, sheetIndex, rowIndex, colsCount, notNull);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        // 生成Excel文件
        return null;
    }

    /**
     * 读取xls文件内容
     *
     * @param workbook
     * @param sheetIndex
     * @param colsCount
     * @return
     */
    private static List<List<CellData>> readSheets(Workbook workbook, Integer sheetIndex, Integer rowIndex, Integer colsCount, boolean notNull) {
        // 结果列表
        List<List<CellData>> datas = Lists.newArrayList();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
            // 找到sheet
            if (numSheet == sheetIndex) {
                // 读取单个sheet
                Sheet sheet = workbook.getSheetAt(numSheet);
                // 读取单个sheet
                return readSheet(sheet, rowIndex, colsCount, notNull);
            }
        }
        // 返回数据
        return datas;
    }

    /**
     * 读取单个sheet
     *
     * @param sheet
     * @param colsCount
     */
    private static List<List<CellData>> readSheet(Sheet sheet, Integer rowIndex, Integer colsCount, boolean notNull) {
        // 如果Sheet名称不对
        if (sheet == null) {
            // 中断流程
            return null;
        }
        // 多行数据
        List<List<CellData>> rowDatas = Lists.newArrayList();
        // 循环行Row
        for (int rowNum = rowIndex; rowNum <= sheet.getLastRowNum(); rowNum++) {
            // 取得单行对象
            Row row = sheet.getRow(rowNum);
            // 读取单行数据
            List<CellData> rowData = readRow(row, colsCount, notNull);
            // 异常处理
            if (judgeRowHasData(rowData)) {
                // 添加元素
                rowDatas.add(rowData);
            }
        }
        // 返回数据
        return rowDatas;
    }

    /**
     * 判断行是否有数据
     *
     * @param rowData
     * @return
     */
    private static boolean judgeRowHasData(List<CellData> rowData) {
        // 异常处理
        if (null == rowData) {
            // 中断流程
            return false;
        }
        // 不包含数据
        boolean hasData = false;
        // 循环处理
        for (CellData cellData : rowData) {
            // 如果有值不为空
            if (null != cellData.getValue() && StringUtils.isNotBlank(cellData.getValue().toString())) {
                // 设置为包含数据
                hasData = true;
            }
        }
        // 返回数据
        return hasData;
    }

    /**
     * 读取单行数据
     *
     * @param row
     * @param colsCount
     */
    private static List<CellData> readRow(Row row, Integer colsCount, boolean notNull) {
        // 异常处理
        if (row == null) {
            // 中断流程
            return null;
        }
        // 行数据
        List<CellData> data = Lists.newArrayList();
        // 循环处理
        for (int cellNum = 0; cellNum < colsCount; cellNum++) {
            // 取得当前单元格
            Cell cell = row.getCell(cellNum);
            // 异常处理
            if (null != cell) {
                // 获取单元格值
                CellData cellData = new CellData(cell.getRowIndex(), cell.getColumnIndex(), null, null, null);
                // 取得值
                Object val = getValue(cell, cellData);
                // 设定值
                cellData.setValue(val);
                // 添加数据
                data.add(cellData);
            } else {
                // 如果可以为空的情况下
                if (!notNull) {
                    // 获取单元格值
                    CellData cellData = new CellData(row.getRowNum(), cellNum, null, null, null);
                    // 添加数据
                    data.add(cellData);
                }
            }
        }
        // 返回数据
        return data;
    }

    /**
     * 得到Excel表中的值
     *
     * @param cell Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    @SuppressWarnings("static-access")
    private static Object getValue(Cell cell, CellData cellData) {
        // 异常判断
        if (cell == null) {
            // 中断流程
            return "";
        }
        // 设定单元格类型
        cellData.setCellType(cell.getCellTypeEnum());
        // 判断单元格数据的类型，不同类型调用不同的方法
        switch (cell.getCellTypeEnum()) {
            // 数值类型
            case NUMERIC:
                // 进一步判断 ，单元格格式是日期格式
                if (DateUtil.isCellDateFormatted(cell)) {
                    // 是日期格式
                    cellData.setDateCell(checkDateCell(cell));
                    // 取得日期值
                    return cell.getDateCellValue();
                }
                // 取得当前数值
                Double douVal = cell.getNumericCellValue();
                // 格式化字符串
                String strVal = str_df.format(douVal);
                // 转换为Double
                Double douCast = CastUtils.castToDouble(strVal);
                // 如果二者值相等
                if (douVal.equals(douCast)) {
                    // 返回字符串值
                    return strVal;
                }
                // 返回double值
                return dou_df.format(douVal);
            case STRING:
                // 字符串
                return cell.getStringCellValue();
            case BOOLEAN:
                // 布尔值
                return cell.getBooleanCellValue();
            case FORMULA:
                try {
                    // 判断单元格是公式格式，需要做一种特殊处理来得到相应的值
                    return cell.getCellFormula();
                } catch (IllegalStateException e) {
                    // 异常情况
                    return cell.getRichStringCellValue().getString();
                }
            case BLANK:
                return "";
            case ERROR:
                return "";
            default:
                return cell.toString().trim();
        }
    }

    private static boolean checkDateCell(Cell cell) {
        // 取得单元格样式
        CellStyle style = cell.getCellStyle();
        // 异常处理
        if (null == style) {
            // 中断流程
            return false;
        }
        // 取得格式化字符串
        String format = style.getDataFormatString();
        // 异常处理
        if (StringUtils.isBlank(format)) {
            // 中断流程
            return false;
        }
        // 判断正则
        if (!Pattern.matches(DATE_FORMAT_REG, format)) {
            // 异常处理
            return false;
        }
        // 中断流程
        return true;
    }
}