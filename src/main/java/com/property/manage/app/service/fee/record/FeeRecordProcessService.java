package com.property.manage.app.service.fee.record;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.property.manage.app.model.po.fee.record.*;
import com.property.manage.app.model.vo.record.FeeRecordIO;
import com.property.manage.app.service.fee.item.FeeItemProcessService;
import com.property.manage.app.service.lessee.company.LesseeCompanyProcessService;
import com.property.manage.app.service.user.UserInfoProcessService;
import com.property.manage.base.excel.model.CellData;
import com.property.manage.base.excel.model.Header;
import com.property.manage.base.excel.service.ExportExcel;
import com.property.manage.base.excel.utils.ExcelUtils;
import com.property.manage.base.excel.utils.ReadUtils;
import com.property.manage.base.model.constants.CommonConstants;
import com.property.manage.base.model.constants.Constants;
import com.property.manage.base.model.exception.ParameterException;
import com.property.manage.base.model.model.Result;
import com.property.manage.base.model.utils.CastUtils;
import com.property.manage.base.model.utils.CheckUtils;
import com.property.manage.common.enums.PayStatus;
import com.property.manage.common.enums.TicketStatus;
import com.property.manage.common.enums.UserTypes;
import com.property.manage.common.pojo.*;
import com.property.manage.common.query.FeeRecordQuery;
import com.property.manage.common.query.FeeRecordViewQuery;
import com.property.manage.common.service.FeeItemService;
import com.property.manage.common.service.FeeRecordService;
import com.property.manage.common.service.LesseeCompanyService;
import com.property.manage.common.service.UserInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 管辉俊
 * @since 2018-05-22
 */
@Service
public class FeeRecordProcessService {

    private static Logger logger = LoggerFactory.getLogger(FeeRecordProcessService.class);

    @Resource
    private FeeRecordService feeRecordService;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private UserInfoProcessService userInfoProcessService;

    @Resource
    private LesseeCompanyService lesseeCompanyService;

    @Resource
    private LesseeCompanyProcessService lesseeCompanyProcessService;

    @Resource
    private FeeItemService feeItemService;

    @Resource
    private FeeItemProcessService feeItemProcessService;

    @Resource
    private FeeRecordUploadProcessService feeRecordUploadProcessService;

    // 表头数组
    public final static String[] head = {"所属月份:theMonth", "收费项目:itemName", "单元编号:unitNumber", "应收金额:planPayFee", "实收金额:realPayFee", "缴费时间:payTime", "缴费状态:payStatus", "开票时间:ticketTime", "开票状态:ticketStatus"};

    private final static Long userId = 0L;

    /**
     * 查询收费列表
     *
     * @param params
     * @return
     * @throws ParameterException
     */
    public Result<FeeRecordView> feeRecordResult(FeeRecordListParams params) throws ParameterException {
        // 查询参数
        FeeRecordViewQuery query = new FeeRecordViewQuery();
        // 用户类型
        query.setItemName(params.getItemName());
        // 企业名称
        query.setCompanyName(params.getCompanyName());
        // 单元编号
        query.setUnitNumber(params.getUnitNumber());
        // 所属月份
        query.setTheMonth(params.getTheMonth());
        // 缴费状态
        query.setPayStatus(params.getPayStatus());
        // 缴费开始时间
        query.setPayTimeStart(params.getPayTimeFrom());
        // 缴费结束时间
        query.setPayTimeEnd(params.getPayTimeTo());
        // 开票开始时间
        query.setTicketTimeStart(params.getTicketTimeFrom());
        // 开票结束时间
        query.setTicketTimeEnd(params.getTicketTimeTo());
        // 按创建时间排序
        query.orderbyAddTime(true);
        // 页码
        query.setPage(params.getPage());
        // 显示条数
        query.setPageSize(params.getPageSize());
        // 返回结果
        return feeRecordService.getFeeRecordViewListWithPage(query);
    }

    /**
     * 查询收费列表(我的)
     *
     * @param params
     * @return
     * @throws ParameterException
     */
    public Result<FeeRecordView> ownerFeeRecordResult(UserInfo userInfo, FeeRecordListParams params) throws ParameterException {
        // 查询参数
        FeeRecordViewQuery query = new FeeRecordViewQuery();
        // 企业ID
        query.setCompanyId(userInfo.getCompanyId());
        // 用户类型
        query.setItemName(params.getItemName());
        // 企业名称
        query.setCompanyName(params.getCompanyName());
        // 单元编号
        query.setUnitNumber(params.getUnitNumber());
        // 所属月份
        query.setTheMonth(params.getTheMonth());
        // 缴费状态
        query.setPayStatus(params.getPayStatus());
        // 缴费开始时间
        query.setPayTimeStart(params.getPayTimeFrom());
        // 缴费结束时间
        query.setPayTimeEnd(params.getPayTimeTo());
        // 开票开始时间
        query.setTicketTimeStart(params.getTicketTimeFrom());
        // 开票结束时间
        query.setTicketTimeEnd(params.getTicketTimeTo());
        // 按创建时间排序
        query.orderbyAddTime(true);
        // 页码
        query.setPage(params.getPage());
        // 显示条数
        query.setPageSize(params.getPageSize());
        // 返回结果
        return feeRecordService.getFeeRecordViewListWithPage(query);
    }

    /**
     * 查询费用列表
     *
     * @param companyId
     * @param itemId
     * @param theMonth
     * @return
     */
    private List<FeeRecord> findFeeRecordList(Long companyId, Long itemId, String theMonth) {
        // 查询对象
        FeeRecordQuery query = new FeeRecordQuery();
        // 用户iD
        query.setCompanyId(companyId);
        // 收费项目ID
        query.setItemId(itemId);
        // 费用所属月份
        query.setTheMonth(theMonth);
        // 返回查询结果
        return feeRecordService.getFeeRecordList(query);
    }

    /**
     * 新增收费记录
     *
     * @param params
     * @throws ParameterException
     */
    public void feeReocrdAdd(UserInfo userInfo, FeeRecordAddParams params) throws ParameterException {
        // 如果权限小于普通员工
        if (userInfo.getUserType() < UserTypes.OPEARTOR.getKey()) {
            // 中断流程
            throw new ParameterException("您无权进行此操作!");
        }
        // 租户企业
        CheckUtils.ObjectNotNull(params.getCompanyId(), "租户企业", null);
        // 收费项目
        CheckUtils.ObjectNotNull(params.getItemId(), "收费项目", null);
        // 所属月份
        CheckUtils.StringNotBlank(params.getTheMonth(), "所属月份", null);
        // 应收金额
        CheckUtils.ObjectNotNull(params.getPlanPayFee(), "应收金额", null);
        // 取得用户信息
        LesseeCompany company = lesseeCompanyService.getLesseeCompanyByKey(params.getCompanyId());
        // 用户不存在
        if (null == company) {
            // 中断流程
            throw new ParameterException("租户企业不存在!");
        }
        // 取得收费项目信息
        FeeItem item = feeItemService.getFeeItemByKey(params.getItemId());
        // 收费项目不存在
        if (null == item) {
            // 中断流程
            throw new ParameterException("收费项目不存在!");
        }
        // 按用户,项目,月份查找记录
        List<FeeRecord> records = findFeeRecordList(params.getCompanyId(), params.getItemId(), params.getTheMonth());
        // 异常处理
        if (CollectionUtils.isNotEmpty(records)) {
            // 中断流程
            throw new ParameterException("租户单元编号[" + company.getUnitNumber() + "]的收费项目[" + item.getItemName() + "]在月份[" + params.getTheMonth() + "]已经存在记录!");
        }
        // 实例化对象
        FeeRecord record = new FeeRecord();
        // 用户ID
        record.setCompanyId(params.getCompanyId());
        // 项目ID
        record.setItemId(params.getItemId());
        // 所属月份
        record.setTheMonth(params.getTheMonth());
        // 应收金额
        record.setPlanPayFee(params.getPlanPayFee());
        // 实收金额
        record.setRealPayFee(BigDecimal.ZERO);
        // 缴费状态
        record.setPayStatus(PayStatus.UN_PAY.getKey());
        // 开票状态
        record.setTicketStatus(TicketStatus.UN_TICKET.getKey());
        // 创建时间
        record.setAddTime(new Date());
        // 更新时间
        record.setUpdTime(new Date());
        // 新增数据
        feeRecordService.addFeeRecord(record);
    }

    /**
     * 更新收费项目
     *
     * @param params
     * @throws ParameterException
     */
    public void feeRecordUpd(UserInfo userInfo, FeeRecordUpdParams params) throws ParameterException {
        // 如果权限小于普通员工
        if (userInfo.getUserType() < UserTypes.OPEARTOR.getKey()) {
            // 中断流程
            throw new ParameterException("您无权进行此操作!");
        }
        // 收费项目ID
        CheckUtils.ObjectNotNull(params.getRecordId(), "收费记录ID", null);
        // 查找对应的项目
        FeeRecord feeRecord = feeRecordService.getFeeRecordByKey(params.getRecordId());
        // 异常处理
        if (null == feeRecord) {
            // 中断流程
            throw new ParameterException("收费记录不存在");
        }
        // 如果权限小于超级管理员
        if (userInfo.getUserType() < UserTypes.SUPER_ADMIN.getKey()) {
            // 如果已经缴费
            if (feeRecord.getPayStatus().equals(PayStatus.PAY.getKey())) {
                // 中断流程
                throw new ParameterException("收费记录已缴费不能修改");
            }
        }
        // 租户企业
        CheckUtils.ObjectNotNull(params.getCompanyId(), "租户企业", null);
        // 收费项目
        CheckUtils.ObjectNotNull(params.getItemId(), "收费项目", null);
        // 所属月份
        CheckUtils.StringNotBlank(params.getTheMonth(), "所属月份", null);
        // 应收金额
        CheckUtils.ObjectNotNull(params.getPlanPayFee(), "应收金额", null);
        // 取得用户信息
        LesseeCompany company = lesseeCompanyService.getLesseeCompanyByKey(params.getCompanyId());
        // 用户不存在
        if (null == company) {
            // 中断流程
            throw new ParameterException("租户企业不存在!");
        }
        // 取得收费项目信息
        FeeItem item = feeItemService.getFeeItemByKey(params.getItemId());
        // 收费项目不存在
        if (null == item) {
            // 中断流程
            throw new ParameterException("收费项目不存在!");
        }
        // 按用户,项目,月份查找记录
        List<FeeRecord> records = findFeeRecordList(params.getCompanyId(), params.getItemId(), params.getTheMonth());
        // 异常处理
        if (CollectionUtils.isNotEmpty(records)) {
            // 循环处理
            for (FeeRecord record : records) {
                // 如果不是同一条数据
                if (!record.getId().equals(feeRecord.getId())) {
                    // 中断流程
                    throw new ParameterException("租户单元编号[" + company.getUnitNumber() + "]的收费项目[" + item.getItemName() + "]在月份[" + params.getTheMonth() + "]已经存在记录!");
                }
            }
        }
        // 企业ID
        feeRecord.setCompanyId(params.getCompanyId());
        // 项目ID
        feeRecord.setItemId(params.getItemId());
        // 所属月份
        feeRecord.setTheMonth(params.getTheMonth());
        // 应收金额
        feeRecord.setPlanPayFee(params.getPlanPayFee());
        // 更新时间
        feeRecord.setUpdTime(new Date());
        // 新增数据
        feeRecordService.updateFeeRecordByKey(feeRecord);
    }

    /**
     * 收费记录详情
     *
     * @param userInfo
     * @param params
     * @return
     * @throws ParameterException
     */
    public FeeRecordView feeRecordDetail(UserInfo userInfo, FeeRecordDetailParams params) throws ParameterException {
        // 取得记录详情
        return feeRecordService.getFeeRecordViewByKey(params.getRecordId());
    }

    /**
     * 删除收费记录
     *
     * @param params
     * @throws ParameterException
     */
    public void feeRecordDel(UserInfo userInfo, FeeRecordDelParams params) throws ParameterException {
        // 如果权限小于管理员
        if (userInfo.getUserType() < UserTypes.OPEARTOR.getKey()) {
            // 中断流程
            throw new ParameterException("您无权进行此操作!");
        }
        // 收费项目名称
        CheckUtils.ObjectNotNull(params.getRecordId(), "收费记录ID", null);
        // 查找对应的项目
        FeeRecord feeRecord = feeRecordService.getFeeRecordByKey(params.getRecordId());
        // 异常处理
        if (null == feeRecord) {
            // 中断流程
            throw new ParameterException("收费记录不存在");
        }
        // 如果权限小于超级管理员
        if (userInfo.getUserType() < UserTypes.SUPER_ADMIN.getKey()) {
            // 如果已经缴费
            if (feeRecord.getPayStatus().equals(PayStatus.PAY.getKey())) {
                // 中断流程
                throw new ParameterException("收费记录已缴费不能删除");
            }
        }
        // 删除记录
        feeRecordService.deleteByKey(feeRecord.getId());
    }

    /**
     * 缴费状态修改
     *
     * @param userInfo
     * @param params
     * @throws ParameterException
     */
    public void feeRecordPay(UserInfo userInfo, FeeRecordPayParams params) throws ParameterException {
        // 如果权限小于管理员
        if (userInfo.getUserType() < UserTypes.OPEARTOR.getKey()) {
            // 中断流程
            throw new ParameterException("您无权进行此操作!");
        }
        // 收费记录ID
        CheckUtils.ObjectNotNull(params.getRecordId(), "收费记录ID", null);
        // 实收金额
        CheckUtils.ObjectNotNull(params.getRealPayFee(), "实收金额", null);
        // 查找对应的项目
        FeeRecord feeRecord = feeRecordService.getFeeRecordByKey(params.getRecordId());
        // 异常处理
        if (null == feeRecord) {
            // 中断流程
            throw new ParameterException("收费记录不存在");
        }
        // 如果已经缴费
        if (feeRecord.getPayStatus().equals(PayStatus.PAY.getKey())) {
            // 中断流程
            throw new ParameterException("收费记录已缴费!");
        }
        // 当前时间
        Date now = new Date();
        // 缴费状态
        feeRecord.setPayStatus(PayStatus.PAY.getKey());
        // 实收金额
        feeRecord.setRealPayFee(params.getRealPayFee());
        // 缴费时间
        feeRecord.setPayTime(null == params.getPayTime() ? now : params.getPayTime());
        // 更新时间
        feeRecord.setUpdTime(now);
        // 更新数据
        feeRecordService.updateFeeRecordByKey(feeRecord);
    }

    /**
     * 收费记录开票
     *
     * @param userInfo
     * @param params
     * @throws ParameterException
     */
    public void feeRecordTicket(UserInfo userInfo, FeeRecordTicketParams params) throws ParameterException {
        // 如果权限小于管理员
        if (userInfo.getUserType() < UserTypes.OPEARTOR.getKey()) {
            // 中断流程
            throw new ParameterException("您无权进行此操作!");
        }
        // 收费记录ID
        CheckUtils.ObjectNotNull(params.getRecordId(), "收费记录ID", null);
        // 查找对应的项目
        FeeRecord feeRecord = feeRecordService.getFeeRecordByKey(params.getRecordId());
        // 异常处理
        if (null == feeRecord) {
            // 中断流程
            throw new ParameterException("收费记录不存在");
        }
        // 如果未缴费
        if (!feeRecord.getPayStatus().equals(PayStatus.PAY.getKey())) {
            // 中断流程
            throw new ParameterException("收费记录还未缴费不能开票!");
        }
        // 如果已经开票
        if (feeRecord.getTicketStatus().equals(TicketStatus.TICKET.getKey())) {
            // 中断流程
            throw new ParameterException("收费记录已开票!");
        }
        // 当前时间
        Date now = new Date();
        // 开票状态
        feeRecord.setTicketStatus(TicketStatus.TICKET.getKey());
        // 开票时间
        feeRecord.setTicketTime(null == params.getTicketTime() ? now : params.getTicketTime());
        // 更新时间
        feeRecord.setUpdTime(now);
        // 更新数据
        feeRecordService.updateFeeRecordByKey(feeRecord);
    }

    /**
     * 下载导入模板
     *
     * @param rsp
     * @throws ParameterException
     */
    public void uploadTemplate(HttpServletResponse rsp) throws ParameterException {
        // 实例化导出对象
        ExportExcel exportExcel = new ExportExcel();
        // 设定表头
        exportExcel.setHeader(ExcelUtils.excelHeader(head));
        // 下载Excel
        exportExcel.downloadExcel("收费记录导入模板", null, rsp);
    }

    /**
     * 批量导入数据
     *
     * @param file
     * @return
     * @throws ParameterException
     */
    public FeeRecordUpload upload(MultipartFile file) throws ParameterException {
        // 取得上传记录
        FeeRecordUpload upload = feeRecordUploadProcessService.findFeeRecordUploadExceptJson(userId);
        // 如果正在导入
        if (null != upload && upload.getStatus() == Constants.UPLOAD_STATUS_DOING) {
            // 中断流程
            throw new ParameterException("您还有正在导入的数据,请稍后再继续!");
        }
        // 表头列表
        List<Header> headers = ExcelUtils.excelHeader(head);
        // 异常处理
        if (null == headers || headers.isEmpty()) {
            // 中断流程
            throw new ParameterException("表头生成失败!");
        }
        // 生成导入Excel数据
        List<Map<String, CellData>> excelDatas = ReadUtils.makeExcelCellDatas(file, headers);
        // 更新上传记录
        upload = feeRecordUploadProcessService.makeFeeRecordUpload(upload, userId, excelDatas.size(), 0, null, Constants.UPLOAD_STATUS_DOING);
        // 批量导入客户数据
        List<Map<String, CellData>> errorDatas = uploadFeeRecords(excelDatas);
        // 更新上传记录
        feeRecordUploadProcessService.makeFeeRecordUpload(upload, userId, excelDatas.size(), errorDatas.size(), JSONArray.toJSONStringWithDateFormat(errorDatas, Constants.yyyy_MM_dd_HH_mm_ss), null);
        // 清除记录
        upload.setErrorJson(CommonConstants.STR_BLANK);
        // 返回上传记录
        return upload;
    }

    /**
     * 导入记录列表
     *
     * @param excelDatas
     * @return
     * @throws ParameterException
     */
    private List<Map<String, CellData>> uploadFeeRecords(List<Map<String, CellData>> excelDatas) throws ParameterException {
        // 错误记录
        List<Map<String, CellData>> errorDatas = Lists.newArrayList();
        // 循环处理
        for (Map<String, CellData> cellDatas : excelDatas) {
            try {
                // 上传单个客户
                uploadFeeRecord(cellDatas);
            } catch (Exception e) {
                logger.error("===批量导入记录===" + e.getMessage());
                // 添加到错误列表中
                errorDatas.add(cellDatas);
            }
        }
        // 返回错误数据
        return errorDatas;
    }

    /**
     * 导入单条记录
     *
     * @param cellDatas
     * @throws ParameterException
     */
    private void uploadFeeRecord(Map<String, CellData> cellDatas) throws ParameterException {
        // 转换数据
        Map<String, Object> datas = ExcelUtils.convCellDatas(cellDatas);
        // 转换对象
        FeeRecordIO io = parseFeeRecord(cellDatas, datas);
        // 简单检查
        recordSimpleCheck(cellDatas, io);
        // 简单检查异常抛出
        ExcelUtils.cellDataCheck(cellDatas, null);
        // 收费项目检查
        FeeItem item = feeItemCheck(io, cellDatas);
        // 单元用户检查
        LesseeCompany company = lesseeCompanyCheck(io, cellDatas);
        // 简单检查异常抛出
        ExcelUtils.cellDataCheck(cellDatas, null);
        // 上传单条记录
        updateFeeRecord(io, item, company);
    }

    /**
     * 上传单条记录
     *
     * @param io
     * @param item
     * @param company
     * @throws ParameterException
     */
    private void updateFeeRecord(FeeRecordIO io, FeeItem item, LesseeCompany company) throws ParameterException {
        // 实例化对象
        FeeRecord record = new FeeRecord();
        // 用户ID
        record.setCompanyId(company.getId());
        // 项目ID
        record.setItemId(item.getId());
        // 所属月份
        record.setTheMonth(io.getTheMonth());
        // 应收金额
        record.setPlanPayFee(io.getPlanPayFee());
        // 实收金额
        record.setRealPayFee(null == io.getRealPayFee() ? BigDecimal.ZERO : io.getRealPayFee());
        // 缴费状态
        PayStatus payStatus = StringUtils.isBlank(io.getPayStatus()) ? null : PayStatus.findByValue(io.getPayStatus());
        // 缴费状态
        record.setPayStatus(null == payStatus ? PayStatus.UN_PAY.getKey() : payStatus.getKey());
        // 缴费时间
        record.setPayTime(null == io.getPayTime() ? null : io.getPayTime());
        // 开票时间
        record.setTicketTime(null == io.getTicketTime() ? null : io.getTicketTime());
        // 缴费状态
        TicketStatus ticketStatus = StringUtils.isBlank(io.getTicketStatus()) ? null : TicketStatus.findByValue(io.getTicketStatus());
        // 开票状态
        record.setTicketStatus(null == ticketStatus ? TicketStatus.UN_TICKET.getKey() : ticketStatus.getKey());
        // 创建时间
        record.setAddTime(new Date());
        // 更新时间
        record.setUpdTime(new Date());
        // 新增数据
        feeRecordService.addFeeRecord(record);
    }

    /**
     * 解析记录
     *
     * @param cellDatas
     * @param datas
     * @return
     * @throws ParameterException
     */
    private FeeRecordIO parseFeeRecord(Map<String, CellData> cellDatas, Map<String, Object> datas) throws ParameterException {
        // 转换对象
        FeeRecordIO io = null;
        try {
            // 转换对象
            io = CastUtils.parseJson(JSONObject.toJSONString(datas), FeeRecordIO.class);
            // 如果转换失败
            if (null == io) {
                // 错误信息
                ExcelUtils.makeCellDataError(cellDatas, "theMonth", "本行记录的数据格式异常");
                // 抛出异常
                throw new ParameterException("本行记录的数据格式异常");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            // 错误信息
            ExcelUtils.makeCellDataError(cellDatas, "theMonth", "本行记录的数据格式异常");
            // 抛出异常
            throw new ParameterException("本行记录的数据格式异常");
        }
        // 返回对象
        return io;
    }

    /**
     * 简单检查
     *
     * @param cellDatas
     * @param io
     * @throws ParameterException
     */
    private void recordSimpleCheck(Map<String, CellData> cellDatas, FeeRecordIO io) throws ParameterException {
        // 异常判断
        if (StringUtils.isBlank(io.getTheMonth())) {
            // 错误信息
            ExcelUtils.makeCellDataError(cellDatas, "theMonth", "所属月份必须输入");
        }
        // 异常判断
        if (null == io.getPlanPayFee()) {
            // 错误信息
            ExcelUtils.makeCellDataError(cellDatas, "planPayFee", "应收金额必须输入");
        }
        // 异常判断
        if (StringUtils.isBlank(io.getUnitNumber())) {
            // 错误信息
            ExcelUtils.makeCellDataError(cellDatas, "unitNumber", "单元编号必须输入");
        }
        // 异常判断
        if (StringUtils.isBlank(io.getItemName())) {
            // 错误信息
            ExcelUtils.makeCellDataError(cellDatas, "itemName", "收费项目必须输入");
        }
        // 有任意缴费信息的情况下
        if (StringUtils.isNotBlank(io.getPayStatus()) || null != io.getRealPayFee() || null != io.getPayTime()) {
            // 异常判断
            if (StringUtils.isBlank(io.getPayStatus())) {
                // 错误信息
                ExcelUtils.makeCellDataError(cellDatas, "payStatus", "有任意缴费信息的情况下,缴费状态必须输入");
                // 取得状态
                PayStatus payStatus = PayStatus.findByValue(io.getPayStatus());
                // 异常判断
                if (null == payStatus) {
                    // 错误信息
                    ExcelUtils.makeCellDataError(cellDatas, "payStatus", "缴费状态不正确");
                }
            }
            // 异常判断
            if (null == io.getRealPayFee()) {
                // 错误信息
                ExcelUtils.makeCellDataError(cellDatas, "realPayFee", "有任意缴费信息的情况下,实缴金额必须输入");
            }
            // 异常判断
            if (null == io.getPayTime()) {
                // 错误信息
                ExcelUtils.makeCellDataError(cellDatas, "payTime", "有任意缴费信息的情况下,缴费时间必须输入");
            }
        }
        // 有任意开票信息的情况下
        if (StringUtils.isNotBlank(io.getTicketStatus()) || null != io.getTicketTime()) {
            // 异常判断
            if (StringUtils.isBlank(io.getTicketStatus())) {
                // 错误信息
                ExcelUtils.makeCellDataError(cellDatas, "ticketStatus", "有任意开票信息的情况下,开票状态必须输入");
                // 取得状态
                TicketStatus ticketStatus = TicketStatus.findByValue(io.getTicketStatus());
                // 异常判断
                if (null == ticketStatus) {
                    // 错误信息
                    ExcelUtils.makeCellDataError(cellDatas, "ticketStatus", "开票状态不正确");
                }
            }
            // 异常判断
            if (null == io.getTicketTime()) {
                // 错误信息
                ExcelUtils.makeCellDataError(cellDatas, "ticketTime", "有任意开票信息的情况下,开票时间必须输入");
            }
        }
    }

    /**
     * 收费项目检查
     *
     * @param io
     * @param cellDatas
     * @return
     * @throws ParameterException
     */
    private FeeItem feeItemCheck(FeeRecordIO io, Map<String, CellData> cellDatas) throws ParameterException {
        // 取得信息
        FeeItem item = feeItemProcessService.findFeeItem(io.getItemName());
        // 异常处理
        if (null == item) {
            // 错误信息
            ExcelUtils.makeCellDataError(cellDatas, "itemName", "收费项目不存在");
            // 抛出异常
            throw new ParameterException("收费项目不存在");
        }
        // 返回信息
        return item;
    }

    /**
     * 单元编号对应的租户检查
     *
     * @param io
     * @param cellDatas
     * @return
     * @throws ParameterException
     */
    private LesseeCompany lesseeCompanyCheck(FeeRecordIO io, Map<String, CellData> cellDatas) throws ParameterException {
        // 查找用户
        LesseeCompany company = lesseeCompanyProcessService.findLesseeCompanyByUnit(io.getUnitNumber());
        // 如果没有查找到跟进人名称
        if (null == company) {
            // 错误信息
            ExcelUtils.makeCellDataError(cellDatas, "unitNumber", "单元编号对应的租户不存在");
            // 抛出异常
            throw new ParameterException("单元编号对应的租户不存在");
        }
        // 返回对象
        return company;
    }

    /**
     * 错误记录
     *
     * @param rsp
     * @throws ParameterException
     */
    public void uploadError(HttpServletResponse rsp) throws ParameterException {
        // 取得上传记录
        FeeRecordUpload upload = feeRecordUploadProcessService.findFeeRecordUpload(userId);
        // 异常处理
        if (null == upload) {
            // 中断流程
            throw new ParameterException("您还没有导入过数据!");
        }
        // 如果正在导入
        if (upload.getStatus() == Constants.UPLOAD_STATUS_DOING) {
            // 中断流程
            throw new ParameterException("您还有正在导入的数据,请稍后再继续!");
        }
        // 取得错误数据
        List<Map<String, CellData>> datas = ExcelUtils.makeJsonToListCellDatas(upload.getErrorJson());
        // 没有需要导出的数据
        if (null == datas || datas.isEmpty()) {
            // 中断流程
            throw new ParameterException("没有需要导出的数据!");
        }
        // 表头列表
        List<Header> headers = ExcelUtils.excelHeader(head);
        // 实例化导出对象
        ExportExcel exportExcel = new ExportExcel();
        // 设定表头
        exportExcel.setHeader(headers);
        // 下载Excel
        exportExcel.downloadExcel("收费记录错误数据", datas, rsp);
    }

    /**
     * 上次导入记录
     *
     * @return
     * @throws ParameterException
     */
    public FeeRecordUpload uploadRecord() throws ParameterException {
        // 返回上传记录
        return feeRecordUploadProcessService.findFeeRecordUploadExceptJson(userId);
    }
}