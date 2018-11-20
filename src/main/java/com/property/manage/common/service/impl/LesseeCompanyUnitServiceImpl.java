package com.property.manage.common.service.impl;

import com.property.manage.base.model.model.Result;
import com.property.manage.common.dao.LesseeCompanyUnitDao;
import com.property.manage.common.pojo.LesseeCompanyUnit;
import com.property.manage.common.pojo.LesseeCompanyUnitView;
import com.property.manage.common.query.LesseeCompanyUnitQuery;
import com.property.manage.common.query.LesseeCompanyUnitViewQuery;
import com.property.manage.common.service.LesseeCompanyUnitService;
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
@Service("LesseeCompanyUnitService")
public class LesseeCompanyUnitServiceImpl implements LesseeCompanyUnitService {

    private static Logger logger = LoggerFactory.getLogger(LesseeCompanyUnitServiceImpl.class);

    @Resource
    private LesseeCompanyUnitDao lesseeCompanyUnitDao;

    @Override
    public Long addLesseeCompanyUnit(LesseeCompanyUnit record) {
        try {
            return lesseeCompanyUnitDao.addLesseeCompanyUnit(record);
        } catch (SQLException e) {
            logger.error("dao addLesseeCompanyUnit error.:" + e.getMessage(), e);
        }
        return -1L;
    }

    @Override
    public LesseeCompanyUnit getLesseeCompanyUnitByKey(Long id) {
        try {
            return lesseeCompanyUnitDao.getLesseeCompanyUnitByKey(id);
        } catch (SQLException e) {
            logger.error("dao getLesseeCompanyUnitbyKey error.:" + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Integer deleteByKey(Long id) {
        try {
            return lesseeCompanyUnitDao.deleteByKey(id);
        } catch (SQLException e) {
            logger.error("dao deleteByKey error. :" + e.getMessage(), e);
        }
        return -1;
    }

    @Override
    public Integer deleteByCompany(Long companyId) {
        try {
            return lesseeCompanyUnitDao.deleteByCompany(companyId);
        } catch (SQLException e) {
            logger.error("dao deleteByCompany error. :" + e.getMessage(), e);
        }
        return -1;
    }

    @Override
    public Integer updateLesseeCompanyUnitByKey(LesseeCompanyUnit record) {
        try {
            return lesseeCompanyUnitDao.updateLesseeCompanyUnitByKey(record);
        } catch (SQLException e) {
            logger.error("dao updateLesseeCompanyUnit error.lesseeCompany:" + e.getMessage(), e);
        }
        return -1;
    }

    @Override
    public Result<LesseeCompanyUnit> getLesseeCompanyUnitListWithPage(LesseeCompanyUnitQuery query) {
        Result<LesseeCompanyUnit> rs = null;
        try {
            rs = lesseeCompanyUnitDao.getLesseeCompanyUnitListWithPage(query);
            if (!rs.isSuccess()) {
                logger.error("get LesseeCompanyUnit error." + rs.getErrorMsg());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return rs;
    }

    @Override
    public List<LesseeCompanyUnit> getLesseeCompanyUnitList(LesseeCompanyUnitQuery query) {
        try {
            return lesseeCompanyUnitDao.getLesseeCompanyUnitList(query);
        } catch (SQLException e) {
            logger.error("get LesseeCompanyUnit list error." + e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public Result<LesseeCompanyUnitView> getLesseeCompanyUnitViewListWithPage(LesseeCompanyUnitViewQuery query) {
        Result<LesseeCompanyUnitView> rs = null;
        try {
            rs = lesseeCompanyUnitDao.getLesseeCompanyUnitViewListWithPage(query);
            if (!rs.isSuccess()) {
                logger.error("get LesseeCompanyUnitView error." + rs.getErrorMsg());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return rs;
    }

    @Override
    public List<LesseeCompanyUnitView> getLesseeCompanyUnitViewList(LesseeCompanyUnitViewQuery query) {
        try {
            return lesseeCompanyUnitDao.getLesseeCompanyUnitViewList(query);
        } catch (SQLException e) {
            logger.error("get LesseeCompanyUnitView list error." + e.getMessage(), e);
        }
        return Collections.emptyList();
    }
}
