package com.property.manage.common.dao;

import com.property.manage.base.model.model.Result;
import com.property.manage.common.mappers.FeeRecordMapper;
import com.property.manage.common.pojo.FeeRecord;
import com.property.manage.common.pojo.FeeRecordView;
import com.property.manage.common.query.FeeRecordQuery;
import com.property.manage.common.query.FeeRecordViewQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author 管辉俊
 */

@Repository
public class FeeRecordDao {

    private static Logger logger = LoggerFactory.getLogger(FeeRecordDao.class);

    @Autowired
    private FeeRecordMapper feeRecordMapper;

    /**
     * 新增数据
     *
     * @return
     * @throws SQLException
     */
    public Long addFeeRecord(FeeRecord record) throws SQLException {
        int insert = feeRecordMapper.insertFeeRecord(record);
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
    public FeeRecord getFeeRecordByKey(Long id) throws SQLException {
        return feeRecordMapper.getFeeRecordByKey(id);
    }

    /**
     * 根据主键查找
     *
     * @throws SQLException
     */
    public FeeRecordView getFeeRecordViewByKey(Long id) throws SQLException {
        return feeRecordMapper.getFeeRecordViewByKey(id);
    }

    /**
     * 根据主键删除
     *
     * @return
     * @throws SQLException
     */
    public Integer deleteByKey(Long id) throws SQLException {
        return feeRecordMapper.deleteByKey(id);
    }

    /**
     * 根据主键删除
     *
     * @return
     * @throws SQLException
     */
    public Integer deleteByCompany(Long companyId) throws SQLException {
        return feeRecordMapper.deleteByCompany(companyId);
    }

    /**
     * 根据主键更新
     *
     * @return
     * @throws SQLException
     */
    public Integer updateFeeRecordByKey(FeeRecord record) throws SQLException {
        return feeRecordMapper.updateFeeRecordByKey(record);
    }

    /**
     * 按条件分页查询
     *
     * @param query
     * @return
     */
    public Result<FeeRecord> getFeeRecordListWithPage(FeeRecordQuery query) throws SQLException {
        Result<FeeRecord> rs = new Result<FeeRecord>();
        Map<String, Object> params = query.getParams();
        Integer count = feeRecordMapper.getFeeRecordListCount(params);
        rs.setCount(count);
        if (count <= 0) {
            return rs;
        }
        try {
            rs.setList(feeRecordMapper.getFeeRecordListWithPage(params));
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
    public List<FeeRecord> getFeeRecordList(FeeRecordQuery query) throws SQLException {
        return feeRecordMapper.getFeeRecordList(query.getParams());
    }

    /**
     * 按条件分页查询
     *
     * @param query
     * @return
     */
    public Result<FeeRecordView> getFeeRecordViewListWithPage(FeeRecordViewQuery query) throws SQLException {
        Result<FeeRecordView> rs = new Result<FeeRecordView>();
        Map<String, Object> params = query.getParams();
        Integer count = feeRecordMapper.getFeeRecordViewListCount(params);
        rs.setCount(count);
        if (count <= 0) {
            return rs;
        }
        try {
            rs.setList(feeRecordMapper.getFeeRecordViewListWithPage(params));
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
    public List<FeeRecordView> getFeeRecordViewList(FeeRecordViewQuery query) throws SQLException {
        return feeRecordMapper.getFeeRecordViewList(query.getParams());
    }
}