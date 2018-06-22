package com.property.manage.common.dao;

import com.property.manage.base.model.model.Result;
import com.property.manage.common.mappers.FeeRecordUploadMapper;
import com.property.manage.common.pojo.FeeRecordUpload;
import com.property.manage.common.query.FeeRecordUploadQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author 管辉俊
 * @date 2018-06-22
 */
@Repository
public class FeeRecordUploadDao {

    private static Logger logger = LoggerFactory.getLogger(FeeRecordUploadDao.class);

    @Autowired
    private FeeRecordUploadMapper feeRecordUploadMapper;

    /**
     * 新增数据
     *
     * @return
     * @throws SQLException
     */
    public Long addFeeRecordUpload(FeeRecordUpload record) throws SQLException {
        int insert = feeRecordUploadMapper.insertFeeRecordUpload(record);
        if (insert > 0) {
            return record.getId();
        }
        return -1L;
    }

    /**
     * 根据主键查找
     *
     * @throws SQLException
     */
    public FeeRecordUpload getFeeRecordUploadByKey(Long id) throws SQLException {
        return feeRecordUploadMapper.getFeeRecordUploadByKey(id);
    }

    /**
     * 根据主键删除
     *
     * @return
     * @throws SQLException
     */
    public Integer deleteByKey(Long id) throws SQLException {
        return feeRecordUploadMapper.deleteByKey(id);
    }

    /**
     * 根据主键更新
     *
     * @return
     * @throws SQLException
     */
    public Integer updateFeeRecordUploadByKey(FeeRecordUpload record) throws SQLException {
        return feeRecordUploadMapper.updateFeeRecordUploadByKey(record);
    }

    /**
     * 按条件分页查询
     *
     * @param query
     * @return
     */
    public Result<FeeRecordUpload> getFeeRecordUploadListWithPage(FeeRecordUploadQuery query) throws SQLException {
        Result<FeeRecordUpload> rs = new Result<FeeRecordUpload>();
        Map<String, Object> params = query.getParams();
        Integer count = feeRecordUploadMapper.getFeeRecordUploadListCount(params);
        rs.setCount(count);
        if (count <= 0) {
            return rs;
        }
        try {
            rs.setList(feeRecordUploadMapper.getFeeRecordUploadListWithPage(params));
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return rs;
    }

    /**
     * 按条件查询
     *
     * @param query
     * @return
     */
    public List<FeeRecordUpload> getFeeRecordUploadList(FeeRecordUploadQuery query) throws SQLException {
        return feeRecordUploadMapper.getFeeRecordUploadList(query.getParams());
    }
}