package com.property.manage.common.service;


import com.property.manage.base.model.model.Result;
import com.property.manage.common.pojo.FeeRecord;
import com.property.manage.common.pojo.FeeRecordView;
import com.property.manage.common.query.FeeRecordQuery;
import com.property.manage.common.query.FeeRecordViewQuery;

import java.util.List;

/**
 * @author 管辉俊
 * @since 2018-06-10
 */
public interface FeeRecordService {

    /**
     * 基本插入
     *
     * @return
     */
    Long addFeeRecord(FeeRecord record);

    /**
     * 根据主键查询
     */
    FeeRecord getFeeRecordByKey(Long id);

    /**
     * 根据主键查询
     */
    FeeRecordView getFeeRecordViewByKey(Long id);

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
    Integer updateFeeRecordByKey(FeeRecord record);

    /**
     * 根据条件查询分页查询
     *
     * @param query 查询条件
     * @return
     */
    Result<FeeRecord> getFeeRecordListWithPage(FeeRecordQuery query);

    /**
     * 根据条件查询
     *
     * @param query 查询条件
     * @return
     */
    List<FeeRecord> getFeeRecordList(FeeRecordQuery query);

    /**
     * 根据条件查询分页查询
     *
     * @param query 查询条件
     * @return
     */
    Result<FeeRecordView> getFeeRecordViewListWithPage(FeeRecordViewQuery query);

    /**
     * 根据条件查询
     *
     * @param query 查询条件
     * @return
     */
    List<FeeRecordView> getFeeRecordViewList(FeeRecordViewQuery query);
}