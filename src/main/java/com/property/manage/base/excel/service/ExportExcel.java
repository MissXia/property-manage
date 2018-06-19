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


import com.google.common.collect.Maps;
import com.property.manage.base.excel.enums.CellStatus;
import com.property.manage.base.excel.model.*;
import com.property.manage.base.excel.model.Header;
import com.property.manage.base.model.utils.AppDateUtils;
import com.property.manage.base.model.utils.CastUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * POI共通类
 *
 * @author guanhj
 * @version 2015年9月7日
 * @see ExportExcel
 */
public class ExportExcel {

    private Logger logger = LoggerFactory.getLogger(ExportExcel.class);

    /**
     * Excel头部
     */
    private List<Header> header = null;

    /**
     * Sheet名称
     */
    private String sheetName = "";

    /**
     * 标题
     */
    private String title = "";

    /**
     * 文件后缀
     */
    public static final String EXCEL_2007_FILE_TYPE = ".xlsx";

    /**
     * 文件后缀
     */
    public static final String EXCEL_2003_FILE_TYPE = ".xls";

    public static final String[] CELL_STYLE_ARRAY = {"title", "head", "cell", "error", "warning", "dateCell", "dateError", "dateWarning", "numCell", "numError", "numWarning"};

    /**
     * 直接下载Excel
     *
     * @param fileName
     * @param path
     * @param response
     * @return
     */
    public boolean downloadExcel(String fileName, List<Map<String, CellData>> datas, HttpServletResponse response) {
        try {
            // 参数异常判断
            if (StringUtils.isBlank(fileName)) {
                // 中断流程
                return false;
            }
            // 参数异常判断
            if (null == header || header.isEmpty()) {
                // 中断流程
                return false;
            }
            // 整理文件名
            fileName = clearFileName(fileName);
            // 创建工作薄
            Workbook workbook = createWorkbook(fileName, datas);
            // 设定字符集
            response.setCharacterEncoding("utf-8");
            // 设定Content类型
            response.setContentType("multipart/form-data");
            // 设定Http头部
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
            // 异常处理
            if (!StringUtils.isNotBlank(fileName)) {
                // 中断流程
                return false;
            }
            // 输出流
            OutputStream os = response.getOutputStream();
            // 写入流
            workbook.write(os);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 导出
     *
     * @param fileName
     * @param datas
     * @return
     */
    public boolean export(String fileName, List<Map<String, CellData>> datas) {
        // 参数异常判断
        if (StringUtils.isBlank(fileName)) {
            // 中断流程
            return false;
        }
        // 参数异常判断
        if (null == header || header.isEmpty()) {
            // 中断流程
            return false;
        }
        // 整理文件名
        fileName = clearFileName(fileName);
        // 创建工作薄
        Workbook workbook = createWorkbook(fileName, datas);
        try {
            // 生成输出流
            FileOutputStream out = new FileOutputStream(fileName);
            // 写工作薄
            workbook.write(out);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 创建工作薄
     *
     * @param fileName
     * @param datas
     * @return
     */
    private Workbook createWorkbook(String fileName, List<Map<String, CellData>> datas) {
        // 工作薄
        Workbook workbook;
        // 2007版本
        if (fileName.endsWith(EXCEL_2007_FILE_TYPE)) {
            // 2007版本
            workbook = new XSSFWorkbook();
        } else {
            // 2003版本
            workbook = new HSSFWorkbook();
        }
        // 创建工作薄
        makeWorkBook(workbook, datas);
        // 返回工作簿
        return workbook;
    }

    /**
     * 整理文件名
     *
     * @param fileName
     * @return
     */
    private String clearFileName(String fileName) {
        // 判断是否以文件后缀结尾
        if (fileName.toLowerCase().endsWith(EXCEL_2003_FILE_TYPE) || fileName.toLowerCase().endsWith(EXCEL_2007_FILE_TYPE)) {
            // 返回文件名
            return fileName;
        }
        // 默认以2007版本
        return fileName + EXCEL_2007_FILE_TYPE;
    }

    /**
     * Description: 创建工作薄<br>
     *
     * @param datas
     * @return workbook
     */
    private Workbook makeWorkBook(Workbook workbook, List<Map<String, CellData>> datas) {
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        Sheet sheet = StringUtils.isNotBlank(sheetName) ? workbook.createSheet(sheetName) : workbook.createSheet();
        // 行游标定义
        int rowIndex = 0;
        // 创建样式
        Map<String, CellStyle> styles = createStyles(workbook);
        // 创建Excel标题
        rowIndex = createTitle(sheet, rowIndex, styles);
        // 创建Excel头部
        rowIndex = createHead(sheet, rowIndex, styles);
        // 创建Excel数据
        createData(workbook, sheet, rowIndex, datas, styles);
        // 返回工作薄
        return workbook;
    }

    /**
     * Description: 创建Excel数据<br>
     *
     * @param workbook
     * @param sheet
     * @param rowIndex
     * @param datas
     */
    private void createData(Workbook workbook, Sheet sheet, int rowIndex, List<Map<String, CellData>> datas, Map<String, CellStyle> styles) {
        // 参数异常判断
        if (null == datas || datas.isEmpty()) {
            // 中断流程
            return;
        }
        // CreationHelper
        CreationHelper factory = workbook.getCreationHelper();
        // Drawing
        Drawing<?> drawing = sheet.createDrawingPatriarch();
        // ClientAnchor
        ClientAnchor anchor = factory.createClientAnchor();
        // 数据长度
        int size = datas.size();
        // 循环处理
        for (int i = 0; i < size; i++) {
            // 当前数据
            Map<String, CellData> data = datas.get(i);
            // 第三步，在sheet中添加第rowIndex行
            Row row = sheet.createRow(i + rowIndex);
            // 设定行数据
            createDataRow(factory, drawing, anchor, row, data, styles);
        }
    }

    /**
     * Description: 设定行数据<br>
     *
     * @param factory
     * @param drawing
     * @param anchor
     * @param row
     * @param data
     * @param styles
     */
    private void createDataRow(CreationHelper factory, Drawing<?> drawing, ClientAnchor anchor, Row row, Map<String, CellData> data, Map<String, CellStyle> styles) {
        // 头部列数
        int size = header.size();
        // 循环处理
        for (int i = 0; i < size; i++) {
            // 当前列
            Header head = header.get(i);
            // 创建当前单元格
            Cell cell = row.createCell(i);
            // 取得filed
            String field = head.getField();
            // 取得值
            CellData cellData = data.get(field);
            // 创建单元格数据
            createDataCell(factory, drawing, anchor, styles, cell, cellData);
        }
    }

    /**
     * 创建单元格数据
     *
     * @param factory
     * @param drawing
     * @param anchor
     * @param styles
     * @param cell
     * @param cellData
     */
    private void createDataCell(CreationHelper factory, Drawing<?> drawing, ClientAnchor anchor, Map<String, CellStyle> styles, Cell cell, CellData cellData) {
        // 异常处理
        if (null == cell) {
            // 中断流程
            return;
        }
        // 生成单元格值
        makeCellValue(cell, cellData);
        // 设定样式
        cell.setCellStyle(changeBodyStyle(styles, cellData));
        // 生成批注
        Comment comment = makeCellComment(factory, drawing, anchor, cellData);
        // 异常判断
        if (null != comment) {
            // 设定批注
            cell.setCellComment(comment);
        }
    }

    /**
     * 生成单元格值
     *
     * @param cell
     * @param cellData
     */
    public void makeCellValue(Cell cell, CellData cellData) {
        // 异常处理
        if (null == cellData) {
            // 中断流程
            return;
        }
        // 取得对应值
        Object value = cellData.getValue();
        // 异常处理
        if (null == cellData.getCellType()) {
            // 转换数字格式
            if (null != value && CastUtils.isNumeric(value.toString())) {
                // 转换值类型
                Double val = CastUtils.castToDouble(value.toString());
                // 设定单元格值为filed值
                cell.setCellValue(null == val ? 0 : val);
            } else {
                // 设定单元格值为filed值
                cell.setCellValue(null == value ? "" : value.toString());
            }
            // 中断流程
            return;
        }
        // 如果是日期类型的
        if (null != cellData.getDateCell() && cellData.getDateCell()) {
            // 解析日期
            Date date = AppDateUtils.parseDate(value.toString());
            // 异常处理
            if (null != date) {
                // 设定值
                cell.setCellValue(date);
            }
            // 中断流程
            return;
        }
        // 判断单元格数据的类型，不同类型调用不同的方法
        switch (cellData.getCellType()) {
            // 数值类型
            case NUMERIC:
                // 转换值类型
                Double val = CastUtils.castToDouble(value.toString());
                // 异常处理
                if (null != val) {
                    // 设定单元格值为filed值
                    cell.setCellValue(val);
                }
                // 中断流程
                break;
            default:
                // 字符串
                cell.setCellValue(null == value ? null : value.toString());
                // 中断流程
                break;
        }
    }

    /**
     * 生成批注
     *
     * @param factory
     * @param drawing
     * @param anchor
     * @param cellData
     * @return
     */
    private Comment makeCellComment(CreationHelper factory, Drawing<?> drawing, ClientAnchor anchor, CellData cellData) {
        // 如果没有备注
        if (null == cellData || StringUtils.isBlank(cellData.getComment()) || null == factory || null == drawing || null == anchor) {
            // 中断流程
            return null;
        }
        // 单元格状态
        CellStatus status = cellData.getStatus();
        // 创建批注
        Comment comment = drawing.createCellComment(anchor);
        // 设定文本
        comment.setString(factory.createRichTextString(cellData.getComment()));
        // 设定作者
        comment.setAuthor(null == status ? "信息" : status.getDetail());
        // 返回批注
        return comment;
    }

    /**
     * 改变样式
     *
     * @param styles
     * @param cellData
     * @return
     */
    private CellStyle changeBodyStyle(Map<String, CellStyle> styles, CellData cellData) {
        // 第四步，创建单元格，并设置值表头 设置表头居中
        CellStyle style = styles.get(CELL_STYLE_ARRAY[2]);
        // 异常处理
        if (null == cellData) {
            // 中断流程
            return style;
        }
        // 单元格状态
        CellStatus status = cellData.getStatus();
        // 异常处理
        if (null != cellData.getDateCell() && cellData.getDateCell()) {
            // 如果状态为空,默认为正常
            if (null == status) {
                // 中断流程
                return styles.get(CELL_STYLE_ARRAY[5]);
            }
            // 如果是警告
            if (status.equals(CellStatus.WARNING)) {
                // 日期警告
                return styles.get(CELL_STYLE_ARRAY[7]);
            }
            // 如果是错误
            if (status.equals(CellStatus.ERROR)) {
                // 日期错误
                return styles.get(CELL_STYLE_ARRAY[6]);
            }
            // 日期正常
            return styles.get(CELL_STYLE_ARRAY[5]);
        }
        // 取得单元格类型
        CellType cellType = cellData.getCellType();
        // 如果单元格是数值类型
        if (null != cellType && cellType.equals(CellType.NUMERIC)) {
            // 如果状态为空,默认为正常
            if (null == status) {
                // 中断流程
                return styles.get(CELL_STYLE_ARRAY[8]);
            }
            // 如果是警告
            if (status.equals(CellStatus.WARNING)) {
                // 日期警告
                return styles.get(CELL_STYLE_ARRAY[10]);
            }
            // 如果是错误
            if (status.equals(CellStatus.ERROR)) {
                // 日期错误
                return styles.get(CELL_STYLE_ARRAY[9]);
            }
            // 日期正常
            return styles.get(CELL_STYLE_ARRAY[8]);
        }
        // 如果状态为空,默认为正常
        if (null == status) {
            // 中断流程
            return style;
        }
        // 如果是警告
        if (status.equals(CellStatus.WARNING)) {
            // 警告正常
            return styles.get(CELL_STYLE_ARRAY[4]);
        }
        // 如果是错误
        if (status.equals(CellStatus.ERROR)) {
            // 错误正常
            return styles.get(CELL_STYLE_ARRAY[3]);
        }
        // 返回样式
        return style;
    }

    /**
     * Description: 创建Excel头部<br>
     *
     * @param sheet
     * @param rowIndex
     * @return
     */
    private int createHead(Sheet sheet, int rowIndex, Map<String, CellStyle> styles) {
        // 第三步，在sheet中添加表头第rowIndex行
        Row row = sheet.createRow(rowIndex);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        CellStyle style = styles.get(CELL_STYLE_ARRAY[1]);
        // 头部列数
        int size = header.size();
        // 循环处理
        for (int i = 0; i < size; i++) {
            // 当前列
            Header head = header.get(i);
            // 设定列宽
            sheet.setColumnWidth(i, 20 * 256);
            // 创建当前单元格
            Cell cell = row.createCell(i);
            // 设定单元格值为当前列名称
            cell.setCellValue(head.getName());
            // 设定样式
            cell.setCellStyle(style);
        }
        return rowIndex + 1;
    }

    /**
     * Description: 创建Title<br>
     *
     * @param sheet
     * @param rowIndex
     * @return int
     */
    private int createTitle(Sheet sheet, int rowIndex, Map<String, CellStyle> styles) {
        // 如果未设定Title
        if (StringUtils.isBlank(title)) {
            // 中断流程
            return rowIndex;
        }
        // 列数
        int cols = header.size();
        // 第三步，在sheet中添加表头第rowIndex行
        Row row = sheet.createRow(rowIndex);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        CellStyle style = styles.get(CELL_STYLE_ARRAY[0]);
        // 取得第一个单元格
        Cell cell = row.getCell(0);
        if (null == cell) {
            // 创建第一个单元格
            cell = row.createCell(0);
        }
        // 设定Title
        cell.setCellValue(title);
        // 设定Title样式
        cell.setCellStyle(style);
        // 合并单元格
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, cols - 1));
        // 返回行索引
        return rowIndex + 1;
    }

    /**
     * 样式集合
     *
     * @param workbook
     * @return
     */
    private Map<String, CellStyle> createStyles(Workbook workbook) {
        // 样式列表
        Map<String, CellStyle> styles = Maps.newHashMap();
        // 标题样式
        styles.put(CELL_STYLE_ARRAY[0], makeTitleStyle(workbook));
        // 表头样式
        styles.put(CELL_STYLE_ARRAY[1], makeHeadStyle(workbook));
        // 数据样式
        styles.put(CELL_STYLE_ARRAY[2], makeBodyStyle(workbook));
        // 数据样式
        styles.put(CELL_STYLE_ARRAY[3], makeErrorStyle(workbook));
        // 数据样式
        styles.put(CELL_STYLE_ARRAY[4], makeWarningStyle(workbook));
        // 数据样式
        styles.put(CELL_STYLE_ARRAY[5], makeDateCellStyle(workbook));
        // 数据样式
        styles.put(CELL_STYLE_ARRAY[6], makeDateErrorStyle(workbook));
        // 数据样式
        styles.put(CELL_STYLE_ARRAY[7], makeDateWarningStyle(workbook));
        // 数据样式
        styles.put(CELL_STYLE_ARRAY[8], makeNumCellStyle(workbook));
        // 数据样式
        styles.put(CELL_STYLE_ARRAY[9], makeNumErrorStyle(workbook));
        // 数据样式
        styles.put(CELL_STYLE_ARRAY[10], makeNumWarningStyle(workbook));
        // 返回列表
        return styles;
    }

    /**
     * Description: 设定Title样式<br>
     *
     * @param workbook
     * @return CellStyle
     */
    private CellStyle makeTitleStyle(Workbook workbook) {
        // 创建单元格样式
        CellStyle style = workbook.createCellStyle();
        // 设置前景填充样式
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 填充字前景色:紫色
        style.setFillForegroundColor(IndexedColors.VIOLET.getIndex());
        // 左边框
        style.setBorderLeft(BorderStyle.THIN);
        // 右边框
        style.setBorderRight(BorderStyle.THIN);
        // 上边框
        style.setBorderTop(BorderStyle.THIN);
        // 下边框
        style.setBorderBottom(BorderStyle.THIN);
        // 居中对齐
        style.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 创建字体
        Font font = workbook.createFont();
        // 字体
        font.setFontName("Arial");
        // 字号
        font.setFontHeightInPoints((short) 20);
        // 加粗
        font.setBold(true);
        // 颜色
        font.setColor(IndexedColors.WHITE.index);
        // 设定字体
        style.setFont(font);
        // 返回标题样式
        return style;
    }

    /**
     * Description: 设定Head样式<br>
     *
     * @param workbook
     * @return CellStyle
     */
    private CellStyle makeHeadStyle(Workbook workbook) {
        // 创建单元格样式
        CellStyle style = workbook.createCellStyle();
        // 设置前景填充样式
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 填充字前景色:紫色
        style.setFillForegroundColor(IndexedColors.VIOLET.getIndex());
        // 左边框
        style.setBorderLeft(BorderStyle.THIN);
        // 右边框
        style.setBorderRight(BorderStyle.THIN);
        // 上边框
        style.setBorderTop(BorderStyle.THIN);
        // 下边框
        style.setBorderBottom(BorderStyle.THIN);
        // 居中对齐
        style.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 创建字体
        Font font = workbook.createFont();
        // 字体
        font.setFontName("Arial");
        // 字号
        font.setFontHeightInPoints((short) 11);
        // 加粗
        font.setBold(true);
        // 颜色
        font.setColor(IndexedColors.WHITE.index);
        // 设定字体
        style.setFont(font);
        // 返回样式
        return style;
    }

    /**
     * Description: 设定样式<br>
     *
     * @param workbook
     * @return
     */
    private CellStyle makeBodyStyle(Workbook workbook) {
        // 创建单元格样式
        CellStyle style = workbook.createCellStyle();
        // 左对齐
        style.setAlignment(HorizontalAlignment.LEFT);
        // 垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 左边框
        style.setBorderLeft(BorderStyle.THIN);
        // 右边框
        style.setBorderRight(BorderStyle.THIN);
        // 上边框
        style.setBorderTop(BorderStyle.THIN);
        // 下边框
        style.setBorderBottom(BorderStyle.THIN);
        // 创建字体
        Font font = workbook.createFont();
        // 字体
        font.setFontName("Arial");
        // 字号
        font.setFontHeightInPoints((short) 9);
        // 颜色
        font.setColor(IndexedColors.BLACK.index);
        // 设定字体
        style.setFont(font);
        // 返回样式
        return style;
    }

    /**
     * Description: 设定样式<br>
     *
     * @param workbook
     * @return
     */
    private CellStyle makeErrorStyle(Workbook workbook) {
        // 创建单元格样式
        CellStyle style = makeBodyStyle(workbook);
        // 填充背景色:红色
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        // 设置前景填充样式
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 返回样式
        return style;
    }

    /**
     * Description: 设定样式<br>
     *
     * @param workbook
     * @return
     */
    private CellStyle makeWarningStyle(Workbook workbook) {
        // 创建单元格样式
        CellStyle style = makeBodyStyle(workbook);
        // 填充背景色:橙色
        style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        // 设置前景填充样式
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 返回样式
        return style;
    }

    /**
     * Description: 设定样式<br>
     *
     * @param workbook
     * @return
     */
    private CellStyle makeDateCellStyle(Workbook workbook) {
        // 创建单元格样式
        CellStyle style = makeBodyStyle(workbook);
        // 创建格式
        DataFormat format = workbook.createDataFormat();
        // 数据格式
        style.setDataFormat(format.getFormat("yyyy-MM-dd HH:mm:ss"));
        // 返回样式
        return style;
    }

    /**
     * Description: 设定样式<br>
     *
     * @param workbook
     * @return
     */
    private CellStyle makeDateErrorStyle(Workbook workbook) {
        // 创建单元格样式
        CellStyle style = makeDateCellStyle(workbook);
        // 填充背景色:红色
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        // 设置前景填充样式
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 返回样式
        return style;
    }

    /**
     * Description: 设定样式<br>
     *
     * @param workbook
     * @return
     */
    private CellStyle makeDateWarningStyle(Workbook workbook) {
        // 创建单元格样式
        CellStyle style = makeDateCellStyle(workbook);
        // 填充背景色:橙色
        style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        // 设置前景填充样式
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 返回样式
        return style;
    }

    /**
     * Description: 设定样式<br>
     *
     * @param workbook
     * @return
     */
    private CellStyle makeNumCellStyle(Workbook workbook) {
        // 创建单元格样式
        CellStyle style = makeBodyStyle(workbook);
        // 创建格式
        DataFormat format = workbook.createDataFormat();
        // 数据格式
        style.setDataFormat(format.getFormat("####################.########"));
        // 返回样式
        return style;
    }

    /**
     * Description: 设定样式<br>
     *
     * @param workbook
     * @return
     */
    private CellStyle makeNumErrorStyle(Workbook workbook) {
        // 创建单元格样式
        CellStyle style = makeNumCellStyle(workbook);
        // 填充背景色:红色
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        // 设置前景填充样式
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 返回样式
        return style;
    }

    /**
     * Description: 设定样式<br>
     *
     * @param workbook
     * @return
     */
    private CellStyle makeNumWarningStyle(Workbook workbook) {
        // 创建单元格样式
        CellStyle style = makeNumCellStyle(workbook);
        // 填充背景色:橙色
        style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        // 设置前景填充样式
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 返回样式
        return style;
    }

    /**
     * @param header The header to set.
     */
    public void setHeader(List<Header> header) {
        this.header = header;
    }

    /**
     * @param sheetName The sheetName to set.
     */
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    /**
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }
}