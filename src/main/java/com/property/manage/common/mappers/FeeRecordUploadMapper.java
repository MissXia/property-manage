package com.property.manage.common.mappers;

import com.property.manage.common.pojo.FeeRecordUpload;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author 管辉俊
 * @date 2018-06-22
 */
@Repository
public interface FeeRecordUploadMapper {

    /**
     * 新增数据
     *
     * @return
     * @throws SQLException
     */
    Integer insertFeeRecordUpload(FeeRecordUpload record) throws SQLException;

    /**
     * 根据主键查找
     *
     * @throws SQLException
     */
    FeeRecordUpload getFeeRecordUploadByKey(@Param("id") Long id) throws SQLException;

    /**
     * 根据主键删除
     *
     * @return
     * @throws SQLException
     */
    Integer deleteByKey(@Param("id") Long id) throws SQLException;

    /**
     * 根据主键更新
     *
     * @return
     * @throws SQLException
     */
    Integer updateFeeRecordUploadByKey(FeeRecordUpload record) throws SQLException;

    /**
     * 根据参数查找记录数
     *
     * @return
     * @throws SQLException
     */
    Integer getFeeRecordUploadListCount(Map<String, Object> params) throws SQLException;

    /**
     * 根据参数分页查询
     *
     * @return
     * @throws SQLException
     */
    List<FeeRecordUpload> getFeeRecordUploadListWithPage(Map<String, Object> params) throws SQLException;

    /**
     * 按条件查询
     *
     * @return
     */
    List<FeeRecordUpload> getFeeRecordUploadList(Map<String, Object> params) throws SQLException;
}