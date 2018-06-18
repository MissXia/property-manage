package com.property.manage.app.service.fee.record;

import com.property.manage.app.model.po.fee.record.FeeRecordAddParams;
import com.property.manage.app.model.po.fee.record.FeeRecordDelParams;
import com.property.manage.app.model.po.fee.record.FeeRecordListParams;
import com.property.manage.app.model.po.fee.record.FeeRecordUpdParams;
import com.property.manage.base.model.exception.ParameterException;
import com.property.manage.base.model.model.Result;
import com.property.manage.base.model.utils.CheckUtils;
import com.property.manage.common.enums.PayStatus;
import com.property.manage.common.enums.TicketStatus;
import com.property.manage.common.enums.UserTypes;
import com.property.manage.common.pojo.FeeItem;
import com.property.manage.common.pojo.FeeRecord;
import com.property.manage.common.pojo.FeeRecordView;
import com.property.manage.common.pojo.UserInfo;
import com.property.manage.common.query.FeeRecordQuery;
import com.property.manage.common.query.FeeRecordViewQuery;
import com.property.manage.common.service.FeeItemService;
import com.property.manage.common.service.FeeRecordService;
import com.property.manage.common.service.UserInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    private FeeItemService feeItemService;

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
        // 用户昵称
        query.setNickName(params.getNickName());
        // 单元编号
        query.setUnitNumber(params.getUnitNumber());
        // 手机号码
        query.setPhoneNumber(params.getPhoneNumber());
        // 所属月份
        query.setTheMonth(params.getTheMonth());
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
        // 用户ID
        query.setUserId(userInfo.getId());
        // 用户类型
        query.setItemName(params.getItemName());
        // 用户昵称
        query.setNickName(params.getNickName());
        // 单元编号
        query.setUnitNumber(params.getUnitNumber());
        // 手机号码
        query.setPhoneNumber(params.getPhoneNumber());
        // 所属月份
        query.setTheMonth(params.getTheMonth());
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
     * @param userId
     * @param itemId
     * @param theMonth
     * @return
     */
    private List<FeeRecord> findFeeRecordList(Long userId, Long itemId, String theMonth) {
        // 查询对象
        FeeRecordQuery query = new FeeRecordQuery();
        // 用户iD
        query.setUserId(userId);
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
        // 用户
        CheckUtils.ObjectNotNull(params.getUserId(), "用户", null);
        // 收费项目
        CheckUtils.ObjectNotNull(params.getItemId(), "收费项目", null);
        // 所属月份
        CheckUtils.StringNotBlank(params.getTheMonth(), "所属月份", null);
        // 应收金额
        CheckUtils.ObjectNotNull(params.getPlanPayFee(), "应收金额", null);
        // 取得用户信息
        UserInfo info = userInfoService.getUserInfoByKey(params.getUserId());
        // 用户不存在
        if (null == info) {
            // 中断流程
            throw new ParameterException("用户不存在!");
        }
        // 取得收费项目信息
        FeeItem item = feeItemService.getFeeItemByKey(params.getItemId());
        // 收费项目不存在
        if (null == item) {
            // 中断流程
            throw new ParameterException("收费项目不存在!");
        }
        // 按用户,项目,月份查找记录
        List<FeeRecord> records = findFeeRecordList(params.getUserId(), params.getItemId(), params.getTheMonth());
        // 异常处理
        if (CollectionUtils.isNotEmpty(records)) {
            // 中断流程
            throw new ParameterException("用户单元编号[" + userInfo.getUnitNumber() + "]的收费项目[" + item.getItemName() + "]在月份[" + params.getTheMonth() + "]已经存在记录!");
        }
        // 实例化对象
        FeeRecord record = new FeeRecord();
        // 用户ID
        record.setUserId(params.getUserId());
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
        // 用户
        CheckUtils.ObjectNotNull(params.getUserId(), "用户", null);
        // 收费项目
        CheckUtils.ObjectNotNull(params.getItemId(), "收费项目", null);
        // 所属月份
        CheckUtils.StringNotBlank(params.getTheMonth(), "所属月份", null);
        // 应收金额
        CheckUtils.ObjectNotNull(params.getPlanPayFee(), "应收金额", null);
        // 取得用户信息
        UserInfo info = userInfoService.getUserInfoByKey(params.getUserId());
        // 用户不存在
        if (null == info) {
            // 中断流程
            throw new ParameterException("用户不存在!");
        }
        // 取得收费项目信息
        FeeItem item = feeItemService.getFeeItemByKey(params.getItemId());
        // 收费项目不存在
        if (null == item) {
            // 中断流程
            throw new ParameterException("收费项目不存在!");
        }
        // 按用户,项目,月份查找记录
        List<FeeRecord> records = findFeeRecordList(params.getUserId(), params.getItemId(), params.getTheMonth());
        // 异常处理
        if (CollectionUtils.isNotEmpty(records)) {
            // 循环处理
            for (FeeRecord record : records) {
                // 如果不是同一条数据
                if (record.getId().equals(feeRecord.getId())) {
                    // 中断流程
                    throw new ParameterException("用户单元编号[" + userInfo.getUnitNumber() + "]的收费项目[" + item.getItemName() + "]在月份[" + params.getTheMonth() + "]已经存在记录!");
                }
            }
        }
        // 用户ID
        feeRecord.setUserId(params.getUserId());
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
}