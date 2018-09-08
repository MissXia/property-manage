package com.property.manage.common.service.impl;

import com.property.manage.base.model.model.Result;
import com.property.manage.common.dao.LesseeCompanyDao;
import com.property.manage.common.pojo.LesseeCompany;
import com.property.manage.common.pojo.LesseeCompanyView;
import com.property.manage.common.query.LesseeCompanyQuery;
import com.property.manage.common.query.LesseeCompanyViewQuery;
import com.property.manage.common.service.LesseeCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * @author 管辉俊
 * @since 2018-08-20
 */
@Service("LesseeCompanyService")
public class LesseeCompanyServiceImpl implements LesseeCompanyService {

    private static Logger logger = LoggerFactory.getLogger(LesseeCompanyServiceImpl.class);

    @Resource
    private LesseeCompanyDao lesseeCompanyDao;

    @Override
    public Long addLesseeCompany(LesseeCompany record) {
        try {
            return lesseeCompanyDao.addLesseeCompany(record);
        } catch (SQLException e) {
            logger.error("dao addLesseeCompany error.:" + e.getMessage(), e);
        }
        return -1L;
    }

    @Override
    public LesseeCompany getLesseeCompanyByKey(Long id) {
        try {
            return lesseeCompanyDao.getLesseeCompanyByKey(id);
        } catch (SQLException e) {
            logger.error("dao getLesseeCompanybyKey error.:" + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Integer deleteByKey(Long id) {
        try {
            return lesseeCompanyDao.deleteByKey(id);
        } catch (SQLException e) {
            logger.error("dao deleteByKey error. :" + e.getMessage(), e);
        }
        return -1;
    }

    @Override
    public Integer updateLesseeCompanyByKey(LesseeCompany record) {
        try {
            return lesseeCompanyDao.updateLesseeCompanyByKey(record);
        } catch (SQLException e) {
            logger.error("dao updateLesseeCompany error.lesseeCompany:" + e.getMessage(), e);
        }
        return -1;
    }

    @Override
    public Result<LesseeCompany> getLesseeCompanyListWithPage(LesseeCompanyQuery query) {
        Result<LesseeCompany> rs = null;
        try {
            rs = lesseeCompanyDao.getLesseeCompanyListWithPage(query);
            if (!rs.isSuccess()) {
                logger.error("get LesseeCompany error." + rs.getErrorMsg());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return rs;
    }

    @Override
    public List<LesseeCompany> getLesseeCompanyList(LesseeCompanyQuery query) {
        try {
            return lesseeCompanyDao.getLesseeCompanyList(query);
        } catch (SQLException e) {
            logger.error("get LesseeCompany list error." + e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public Result<LesseeCompanyView> getLesseeCompanyViewListWithPage(LesseeCompanyViewQuery query) {
        Result<LesseeCompanyView> rs = null;
        try {
            rs = lesseeCompanyDao.getLesseeCompanyViewListWithPage(query);
            if (!rs.isSuccess()) {
                logger.error("get LesseeCompanyView error." + rs.getErrorMsg());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return rs;
    }

    @Override
    public List<LesseeCompanyView> getLesseeCompanyViewList(LesseeCompanyViewQuery query) {
        try {
            return lesseeCompanyDao.getLesseeCompanyViewList(query);
        } catch (SQLException e) {
            logger.error("get LesseeCompanyView list error." + e.getMessage(), e);
        }
        return Collections.emptyList();
    }
}
