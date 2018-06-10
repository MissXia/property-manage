package com.property.manage.common.service;

import com.property.manage.base.model.model.Result;
import com.property.manage.common.pojo.FeeItem;
import com.property.manage.common.query.FeeItemQuery;

import java.util.List;

/**
 * @author 管辉俊
 * @since 2018-06-10
 */
public interface FeeItemService {

    /**
     * 基本插入
     *
     * @return
     */
    Long addFeeItem(FeeItem record);

    /**
     * 根据主键查询
     */
    FeeItem getFeeItemByKey(Long id);

    /**
     * 根据主键删除
     *
     * @return
     */
    Integer deleteByKey(Long id);

    /**
     * 根据主键更新
     *
     * @return
     */
    Integer updateFeeItemByKey(FeeItem record);

    /**
     * 根据条件查询分页查询
     *
     * @param query 查询条件
     * @return
     */
    Result<FeeItem> getFeeItemListWithPage(FeeItemQuery query);

    /**
     * 根据条件查询
     *
     * @param query 查询条件
     * @return
     */
    List<FeeItem> getFeeItemList(FeeItemQuery query);
}
