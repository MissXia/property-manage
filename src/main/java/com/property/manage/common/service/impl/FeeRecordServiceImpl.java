package com.property.manage.common.service.impl;

import com.property.manage.base.model.model.Result;
import com.property.manage.common.dao.FeeRecordDao;
import com.property.manage.common.pojo.FeeRecord;
import com.property.manage.common.pojo.FeeRecordView;
import com.property.manage.common.query.FeeRecordQuery;
import com.property.manage.common.query.FeeRecordViewQuery;
import com.property.manage.common.service.FeeRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * @author 管辉俊
 * @since 2018-06-10
 */
@Service("FeeRecordService")
public class FeeRecordServiceImpl implements FeeRecordService {

    private static Logger logger = LoggerFactory.getLogger(FeeRecordServiceImpl.class);
    @Resource
    private FeeRecordDao feeRecordDao;

    @Override
    public Long addFeeRecord(FeeRecord record) {
        try {
            return feeRecordDao.addFeeRecord(record);
        } catch (SQLException e) {
            logger.error("dao addFeeRecord error.:" + e.getMessage(), e);
        }
        return -1L;
    }

    @Override
    public FeeRecord getFeeRecordByKey(Long id) {
        try {
            return feeRecordDao.getFeeRecordByKey(id);
        } catch (SQLException e) {
            logger.error("dao getFeeRecordbyKey error.:" + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public FeeRecordView getFeeRecordViewByKey(Long id) {
        try {
            return feeRecordDao.getFeeRecordViewByKey(id);
        } catch (SQLException e) {
            logger.error("dao getFeeRecordViewByKey error.:" + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Integer deleteByKey(Long id) {
        try {
            return feeRecordDao.deleteByKey(id);
        } catch (SQLException e) {
            logger.error("dao deleteByKey error. :" + e.getMessage(), e);
        }
        return -1;
    }

    @Override
    public Integer updateFeeRecordByKey(FeeRecord record) {
        try {
            return feeRecordDao.updateFeeRecordByKey(record);
        } catch (SQLException e) {
            logger.error("dao updateFeeRecord error.feeRecord:" + e.getMessage(), e);
        }
        return -1;
    }


    @Override
    public Result<FeeRecord> getFeeRecordListWithPage(FeeRecordQuery query) {
        Result<FeeRecord> rs = null;
        try {
            rs = feeRecordDao.getFeeRecordListWithPage(query);
            if (!rs.isSuccess()) {
                logger.error("get FeeRecord error." + rs.getErrorMsg());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return rs;
    }

    @Override
    public List<FeeRecord> getFeeRecordList(FeeRecordQuery query) {
        try {
            return feeRecordDao.getFeeRecordList(query);
        } catch (SQLException e) {
            logger.error("get FeeRecord list error." + e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public Result<FeeRecordView> getFeeRecordViewListWithPage(FeeRecordViewQuery query) {
        Result<FeeRecordView> rs = null;
        try {
            rs = feeRecordDao.getFeeRecordViewListWithPage(query);
            if (!rs.isSuccess()) {
                logger.error("get FeeRecordView error." + rs.getErrorMsg());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return rs;
    }

    @Override
    public List<FeeRecordView> getFeeRecordViewList(FeeRecordViewQuery query) {
        try {
            return feeRecordDao.getFeeRecordViewList(query);
        } catch (SQLException e) {
            logger.error("get FeeRecordView list error." + e.getMessage(), e);
        }
        return Collections.emptyList();
    }
}