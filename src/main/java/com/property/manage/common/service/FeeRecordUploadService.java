package com.property.manage.common.service;


import com.property.manage.base.model.model.Result;
import com.property.manage.common.pojo.FeeRecordUpload;
import com.property.manage.common.query.FeeRecordUploadQuery;

import java.util.List;

/**
 * @author 管辉俊
 * @since 2018-06-22
 */
public interface FeeRecordUploadService {

    /**
     * 基本插入
     *
     * @return
     */
    Long addFeeRecordUpload(FeeRecordUpload record);

    /**
     * 根据主键查询
     */
    FeeRecordUpload getFeeRecordUploadByKey(Long id);

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
    Integer updateFeeRecordUploadByKey(FeeRecordUpload record);

    /**
     * 根据条件查询分页查询
     *
     * @param query 查询条件
     * @return
     */
    Result<FeeRecordUpload> getFeeRecordUploadListWithPage(FeeRecordUploadQuery query);

    /**
     * 根据条件查询
     *
     * @param query 查询条件
     * @return
     */
    List<FeeRecordUpload> getFeeRecordUploadList(FeeRecordUploadQuery query);
}
