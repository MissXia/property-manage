package com.property.manage.common.service.impl;

import com.property.manage.base.model.model.Result;
import com.property.manage.common.dao.FeeRecordUploadDao;
import com.property.manage.common.pojo.FeeRecordUpload;
import com.property.manage.common.query.FeeRecordUploadQuery;
import com.property.manage.common.service.FeeRecordUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * @author 管辉俊
 * @since 2018-06-22
 */
@Service("FeeRecordUploadService")
public class FeeRecordUploadServiceImpl implements FeeRecordUploadService {

    private static Logger logger = LoggerFactory.getLogger(FeeRecordUploadServiceImpl.class);
    @Resource
    private FeeRecordUploadDao feeRecordUploadDao;

    @Override
    public Long addFeeRecordUpload(FeeRecordUpload record) {
        try {
            return feeRecordUploadDao.addFeeRecordUpload(record);
        } catch (SQLException e) {
            logger.error("dao addFeeRecordUpload error.:" + e.getMessage(), e);
        }
        return -1L;
    }

    @Override
    public FeeRecordUpload getFeeRecordUploadByKey(Long id) {
        try {
            return feeRecordUploadDao.getFeeRecordUploadByKey(id);
        } catch (SQLException e) {
            logger.error("dao getFeeRecordUploadbyKey error.:" + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Integer deleteByKey(Long id) {
        try {
            return feeRecordUploadDao.deleteByKey(id);
        } catch (SQLException e) {
            logger.error("dao deleteByKey error. :" + e.getMessage(), e);
        }
        return -1;
    }

    @Override
    public Integer updateFeeRecordUploadByKey(FeeRecordUpload record) {
        try {
            return feeRecordUploadDao.updateFeeRecordUploadByKey(record);
        } catch (SQLException e) {
            logger.error("dao updateFeeRecordUpload error.feeRecordUpload:" + e.getMessage(), e);
        }
        return -1;
    }


    @Override
    public Result<FeeRecordUpload> getFeeRecordUploadListWithPage(FeeRecordUploadQuery query) {
        Result<FeeRecordUpload> rs = null;
        try {
            rs = feeRecordUploadDao.getFeeRecordUploadListWithPage(query);
            if (!rs.isSuccess()) {
                logger.error("get FeeRecordUpload error." + rs.getErrorMsg());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return rs;
    }

    @Override
    public List<FeeRecordUpload> getFeeRecordUploadList(FeeRecordUploadQuery query) {
        try {
            return feeRecordUploadDao.getFeeRecordUploadList(query);
        } catch (SQLException e) {
            logger.error("get FeeRecordUpload list error." + e.getMessage(), e);
        }
        return Collections.emptyList();
    }
}