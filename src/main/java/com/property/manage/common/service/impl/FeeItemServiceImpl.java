package com.property.manage.common.service.impl;

import com.property.manage.base.model.model.Result;
import com.property.manage.common.dao.FeeItemDao;
import com.property.manage.common.pojo.FeeItem;
import com.property.manage.common.query.FeeItemQuery;
import com.property.manage.common.service.FeeItemService;
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
@Service("FeeItemService")
public class FeeItemServiceImpl implements FeeItemService {

    private static Logger logger = LoggerFactory.getLogger(FeeItemServiceImpl.class);
    @Resource
    private FeeItemDao feeItemDao;

    @Override
    public Long addFeeItem(FeeItem record) {
        try {
            return feeItemDao.addFeeItem(record);
        } catch (SQLException e) {
            logger.error("dao addFeeItem error.:" + e.getMessage(), e);
        }
        return -1L;
    }

    @Override
    public FeeItem getFeeItemByKey(Long id) {
        try {
            return feeItemDao.getFeeItemByKey(id);
        } catch (SQLException e) {
            logger.error("dao getFeeItembyKey error.:" + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Integer deleteByKey(Long id) {
        try {
            return feeItemDao.deleteByKey(id);
        } catch (SQLException e) {
            logger.error("dao deleteByKey error. :" + e.getMessage(), e);
        }
        return -1;
    }

    @Override
    public Integer updateFeeItemByKey(FeeItem record) {
        try {
            return feeItemDao.updateFeeItemByKey(record);
        } catch (SQLException e) {
            logger.error("dao updateFeeItem error.feeItem:" + e.getMessage(), e);
        }
        return -1;
    }


    @Override
    public Result<FeeItem> getFeeItemListWithPage(FeeItemQuery query) {
        Result<FeeItem> rs = null;
        try {
            rs = feeItemDao.getFeeItemListWithPage(query);
            if (!rs.isSuccess()) {
                logger.error("get FeeItem error." + rs.getErrorMsg());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return rs;
    }

    @Override
    public List<FeeItem> getFeeItemList(FeeItemQuery query) {
        try {
            return feeItemDao.getFeeItemList(query);
        } catch (SQLException e) {
            logger.error("get FeeItem list error." + e.getMessage(), e);
        }
        return Collections.emptyList();
    }
}
