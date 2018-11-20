package com.property.manage.common.mappers;

import com.property.manage.common.pojo.FeeRecord;
import com.property.manage.common.pojo.FeeRecordView;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@Repository
public interface FeeRecordMapper {

    /**
     * 新增数据
     *
     * @return
     * @throws SQLException
     */
    Integer insertFeeRecord(FeeRecord record) throws SQLException;

    /**
     * 根据主键查找
     *
     * @throws SQLException
     */
    FeeRecord getFeeRecordByKey(@Param("id") Long id) throws SQLException;

    /**
     * 根据主键查找
     *
     * @throws SQLException
     */
    FeeRecordView getFeeRecordViewByKey(@Param("id") Long id) throws SQLException;

    /**
     * 根据主键删除
     *
     * @return
     * @throws SQLException
     */
    Integer deleteByKey(@Param("id") Long id) throws SQLException;

    /**
     * 根据主键删除
     *
     * @return
     * @throws SQLException
     */
    Integer deleteByCompany(@Param("companyId") Long companyId) throws SQLException;

    /**
     * 根据主键更新
     *
     * @return
     * @throws SQLException
     */
    Integer updateFeeRecordByKey(FeeRecord record) throws SQLException;

    /**
     * 根据参数查找记录数
     *
     * @return
     * @throws SQLException
     */
    Integer getFeeRecordListCount(Map<String, Object> params) throws SQLException;

    /**
     * 根据参数分页查询
     *
     * @return
     * @throws SQLException
     */
    List<FeeRecord> getFeeRecordListWithPage(Map<String, Object> params) throws SQLException;

    /**
     * 按条件查询
     *
     * @return
     */
    List<FeeRecord> getFeeRecordList(Map<String, Object> params) throws SQLException;

    /**
     * 根据参数查找记录数
     *
     * @return
     * @throws SQLException
     */
    Integer getFeeRecordViewListCount(Map<String, Object> params) throws SQLException;

    /**
     * 根据参数分页查询
     *
     * @return
     * @throws SQLException
     */
    List<FeeRecordView> getFeeRecordViewListWithPage(Map<String, Object> params) throws SQLException;

    /**
     * 按条件查询
     *
     * @return
     */
    List<FeeRecordView> getFeeRecordViewList(Map<String, Object> params) throws SQLException;
}