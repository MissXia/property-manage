package com.property.manage.common.dao;

import com.property.manage.base.model.model.Result;
import com.property.manage.common.mappers.LesseeCompanyUnitMapper;
import com.property.manage.common.pojo.LesseeCompanyUnit;
import com.property.manage.common.pojo.LesseeCompanyUnitView;
import com.property.manage.common.query.LesseeCompanyUnitQuery;
import com.property.manage.common.query.LesseeCompanyUnitViewQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author 管辉俊
 * @date 2018-08-20
 */
@Repository
public class LesseeCompanyUnitDao {

    private static Logger logger = LoggerFactory.getLogger(LesseeCompanyUnitDao.class);

    @Autowired
    private LesseeCompanyUnitMapper lesseeCompanyUnitMapper;

    /**
     * 新增数据
     *
     * @return
     * @throws SQLException
     */
    public Long addLesseeCompanyUnit(LesseeCompanyUnit record) throws SQLException {
        int insert = lesseeCompanyUnitMapper.insertLesseeCompanyUnit(record);
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
    public LesseeCompanyUnit getLesseeCompanyUnitByKey(Long id) throws SQLException {
        return lesseeCompanyUnitMapper.getLesseeCompanyUnitByKey(id);
    }

    /**
     * 根据主键删除
     *
     * @return
     * @throws SQLException
     */
    public Integer deleteByKey(Long id) throws SQLException {
        return lesseeCompanyUnitMapper.deleteByKey(id);
    }

    /**
     * 根据主键删除
     *
     * @return
     * @throws SQLException
     */
    public Integer deleteByCompany(Long companyId) throws SQLException {
        return lesseeCompanyUnitMapper.deleteByCompany(companyId);
    }

    /**
     * 根据主键更新
     *
     * @return
     * @throws SQLException
     */
    public Integer updateLesseeCompanyUnitByKey(LesseeCompanyUnit record) throws SQLException {
        return lesseeCompanyUnitMapper.updateLesseeCompanyUnitByKey(record);
    }

    /**
     * 按条件分页查询
     *
     * @param query
     * @return
     */
    public Result<LesseeCompanyUnit> getLesseeCompanyUnitListWithPage(LesseeCompanyUnitQuery query) throws SQLException {
        Result<LesseeCompanyUnit> rs = new Result<LesseeCompanyUnit>();
        Map<String, Object> params = query.getParams();
        Integer count = lesseeCompanyUnitMapper.getLesseeCompanyUnitListCount(params);
        rs.setCount(count);
        if (count <= 0) {
            return rs;
        }
        try {
            rs.setList(lesseeCompanyUnitMapper.getLesseeCompanyUnitListWithPage(params));
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
    public List<LesseeCompanyUnit> getLesseeCompanyUnitList(LesseeCompanyUnitQuery query) throws SQLException {
        return lesseeCompanyUnitMapper.getLesseeCompanyUnitList(query.getParams());
    }

    /**
     * 按条件分页查询
     *
     * @param query
     * @return
     */
    public Result<LesseeCompanyUnitView> getLesseeCompanyUnitViewListWithPage(LesseeCompanyUnitViewQuery query) throws SQLException {
        Result<LesseeCompanyUnitView> rs = new Result<LesseeCompanyUnitView>();
        Map<String, Object> params = query.getParams();
        Integer count = lesseeCompanyUnitMapper.getLesseeCompanyUnitViewListCount(params);
        rs.setCount(count);
        if (count <= 0) {
            return rs;
        }
        try {
            rs.setList(lesseeCompanyUnitMapper.getLesseeCompanyUnitViewListWithPage(params));
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
    public List<LesseeCompanyUnitView> getLesseeCompanyUnitViewList(LesseeCompanyUnitViewQuery query) throws SQLException {
        return lesseeCompanyUnitMapper.getLesseeCompanyUnitViewList(query.getParams());
    }
}