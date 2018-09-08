package com.property.manage.common.dao;

import com.property.manage.base.model.model.Result;
import com.property.manage.common.mappers.LesseeCompanyMapper;
import com.property.manage.common.pojo.LesseeCompany;
import com.property.manage.common.pojo.LesseeCompanyView;
import com.property.manage.common.query.LesseeCompanyQuery;
import com.property.manage.common.query.LesseeCompanyViewQuery;
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
public class LesseeCompanyDao {

    private static Logger logger = LoggerFactory.getLogger(LesseeCompanyDao.class);

    @Autowired
    private LesseeCompanyMapper lesseeCompanyMapper;

    /**
     * 新增数据
     *
     * @return
     * @throws SQLException
     */
    public Long addLesseeCompany(LesseeCompany record) throws SQLException {
        int insert = lesseeCompanyMapper.insertLesseeCompany(record);
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
    public LesseeCompany getLesseeCompanyByKey(Long id) throws SQLException {
        return lesseeCompanyMapper.getLesseeCompanyByKey(id);
    }

    /**
     * 根据主键删除
     *
     * @return
     * @throws SQLException
     */
    public Integer deleteByKey(Long id) throws SQLException {
        return lesseeCompanyMapper.deleteByKey(id);
    }

    /**
     * 根据主键更新
     *
     * @return
     * @throws SQLException
     */
    public Integer updateLesseeCompanyByKey(LesseeCompany record) throws SQLException {
        return lesseeCompanyMapper.updateLesseeCompanyByKey(record);
    }

    /**
     * 按条件分页查询
     *
     * @param query
     * @return
     */
    public Result<LesseeCompany> getLesseeCompanyListWithPage(LesseeCompanyQuery query) throws SQLException {
        Result<LesseeCompany> rs = new Result<LesseeCompany>();
        Map<String, Object> params = query.getParams();
        Integer count = lesseeCompanyMapper.getLesseeCompanyListCount(params);
        rs.setCount(count);
        if (count <= 0) {
            return rs;
        }
        try {
            rs.setList(lesseeCompanyMapper.getLesseeCompanyListWithPage(params));
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
    public List<LesseeCompany> getLesseeCompanyList(LesseeCompanyQuery query) throws SQLException {
        return lesseeCompanyMapper.getLesseeCompanyList(query.getParams());
    }

    /**
     * 按条件分页查询
     *
     * @param query
     * @return
     */
    public Result<LesseeCompanyView> getLesseeCompanyViewListWithPage(LesseeCompanyViewQuery query) throws SQLException {
        Result<LesseeCompanyView> rs = new Result<LesseeCompanyView>();
        Map<String, Object> params = query.getParams();
        Integer count = lesseeCompanyMapper.getLesseeCompanyViewListCount(params);
        rs.setCount(count);
        if (count <= 0) {
            return rs;
        }
        try {
            rs.setList(lesseeCompanyMapper.getLesseeCompanyViewListWithPage(params));
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
    public List<LesseeCompanyView> getLesseeCompanyViewList(LesseeCompanyViewQuery query) throws SQLException {
        return lesseeCompanyMapper.getLesseeCompanyViewList(query.getParams());
    }
}