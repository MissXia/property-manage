package com.property.manage.app.service.fee.item;

import com.property.manage.app.model.po.fee.item.FeeItemAddParams;
import com.property.manage.app.model.po.fee.item.FeeItemDelParams;
import com.property.manage.app.model.po.fee.item.FeeItemListParams;
import com.property.manage.app.model.po.fee.item.FeeItemUpdParams;
import com.property.manage.base.model.exception.ParameterException;
import com.property.manage.base.model.model.Result;
import com.property.manage.base.model.utils.CheckUtils;
import com.property.manage.common.enums.UserTypes;
import com.property.manage.common.pojo.FeeItem;
import com.property.manage.common.pojo.UserInfo;
import com.property.manage.common.query.FeeItemQuery;
import com.property.manage.common.service.FeeItemService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 管辉俊
 * @since 2018-05-22
 */
@Service
public class FeeItemProcessService {

    private static Logger logger = LoggerFactory.getLogger(FeeItemProcessService.class);

    @Resource
    private FeeItemService feeItemService;

    /**
     * 查询收费列表
     *
     * @param params
     * @return
     * @throws ParameterException
     */
    public Result<FeeItem> feeItemResult(FeeItemListParams params) throws ParameterException {
        // 查询参数
        FeeItemQuery query = new FeeItemQuery();
        // 用户类型
        query.setItemName(params.getItemName());
        // 按创建时间排序
        query.orderbyAddTime(true);
        // 页码
        query.setPage(params.getPage());
        // 显示条数
        query.setPageSize(params.getPageSize());
        // 返回结果
        return feeItemService.getFeeItemListWithPage(query);
    }

    /**
     * 按名称查找项目
     *
     * @param itemName
     * @return
     */
    public List<FeeItem> findFeeItemList(String itemName) {
        // 查询参数
        FeeItemQuery query = new FeeItemQuery();
        // 用户类型
        query.setItemName(itemName);
        // 按创建时间排序
        query.orderbyAddTime(true);
        // 返回结果
        return feeItemService.getFeeItemList(query);
    }

    /**
     * 按名称查找项目
     *
     * @param itemName
     * @return
     */
    public FeeItem findFeeItem(String itemName) {
        // 按名称查找项目
        List<FeeItem> items = findFeeItemList(itemName);
        // 异常处理
        if (CollectionUtils.isEmpty(items)) {
            // 中断流程
            return null;
        }
        // 返回数据
        return items.get(0);
    }

    /**
     * 新增收费项目
     *
     * @param params
     * @throws ParameterException
     */
    public void feeItemAdd(UserInfo userInfo, FeeItemAddParams params) throws ParameterException {
        // 如果权限小于管理员
        if (userInfo.getUserType() < UserTypes.ADMIN.getKey()) {
            // 中断流程
            throw new ParameterException("您无权进行此操作!");
        }
        // 收费项目名称
        CheckUtils.StringNotBlank(params.getItemName(), "收费项目名称", null);
        // 按名称查找项目
        List<FeeItem> items = findFeeItemList(params.getItemName());
        // 异常处理
        if (CollectionUtils.isNotEmpty(items)) {
            // 中断流程
            throw new ParameterException("收费项目名称重复");
        }
        // 实例化对象
        FeeItem item = new FeeItem();
        // 项目名称
        item.setItemName(params.getItemName());
        // 创建时间
        item.setAddTime(new Date());
        // 更新时间
        item.setUpdTime(new Date());
        // 新增数据
        feeItemService.addFeeItem(item);
    }

    /**
     * 更新收费项目
     *
     * @param params
     * @throws ParameterException
     */
    public void feeItemUpd(UserInfo userInfo, FeeItemUpdParams params) throws ParameterException {
        // 如果权限小于管理员
        if (userInfo.getUserType() < UserTypes.ADMIN.getKey()) {
            // 中断流程
            throw new ParameterException("您无权进行此操作!");
        }
        // 收费项目ID
        CheckUtils.ObjectNotNull(params.getItemId(), "收费项目ID", null);
        // 收费项目名称
        CheckUtils.StringNotBlank(params.getItemName(), "收费项目名称", null);
        // 查找对应的项目
        FeeItem feeItem = feeItemService.getFeeItemByKey(params.getItemId());
        // 异常处理
        if (null == feeItem) {
            // 中断流程
            throw new ParameterException("收费项目不存在");
        }
        // 按名称查找项目
        List<FeeItem> items = findFeeItemList(params.getItemName());
        // 异常处理
        if (CollectionUtils.isNotEmpty(items)) {
            // 循环处理
            for (FeeItem item : items) {
                // 如果不是同一条数据
                if (item.getId().equals(feeItem.getId())) {
                    // 中断流程
                    throw new ParameterException("收费项目名称重复");
                }
            }
        }
        // 项目名称
        feeItem.setItemName(params.getItemName());
        // 更新时间
        feeItem.setUpdTime(new Date());
        // 更新数据
        feeItemService.updateFeeItemByKey(feeItem);
    }

    /**
     * 删除收费项目
     *
     * @param params
     * @throws ParameterException
     */
    public void feeItemDel(UserInfo userInfo, FeeItemDelParams params) throws ParameterException {
        // 如果权限小于管理员
        if (userInfo.getUserType() < UserTypes.ADMIN.getKey()) {
            // 中断流程
            throw new ParameterException("您无权进行此操作!");
        }
        // 收费项目名称
        CheckUtils.ObjectNotNull(params.getItemId(), "收费项目ID", null);
        // 查找对应的项目
        FeeItem feeItem = feeItemService.getFeeItemByKey(params.getItemId());
        // 异常处理
        if (null == feeItem) {
            // 中断流程
            throw new ParameterException("收费项目不存在");
        }
        // 删除项目
        feeItemService.deleteByKey(feeItem.getId());
    }
}