package com.property.manage.common.service;

import com.property.manage.base.model.model.Result;
import com.property.manage.common.pojo.LesseeCompanyUnit;
import com.property.manage.common.pojo.LesseeCompanyUnitView;
import com.property.manage.common.query.LesseeCompanyUnitQuery;
import com.property.manage.common.query.LesseeCompanyUnitViewQuery;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 管辉俊
 * @since 2018-08-20
 */
public interface LesseeCompanyUnitService {

    /**
     * 基本插入
     *
     * @return
     */
    Long addLesseeCompanyUnit(LesseeCompanyUnit record);

    /**
     * 根据主键查询
     */
    LesseeCompanyUnit getLesseeCompanyUnitByKey(Long id);

    /**
     * 根据主键删除
     *
     * @return
     */
    Integer deleteByKey(Long id);

    /**
     * 根据主键删除
     *
     * @return
     * @throws SQLException
     */
    Integer deleteByCompany(Long companyId);

    /**
     * 根据主键更新
     *
     * @return
     */
    Integer updateLesseeCompanyUnitByKey(LesseeCompanyUnit record);

    /**
     * 根据条件查询分页查询
     *
     * @param query 查询条件
     * @return
     */
    Result<LesseeCompanyUnit> getLesseeCompanyUnitListWithPage(LesseeCompanyUnitQuery query);

    /**
     * 根据条件查询
     *
     * @param query 查询条件
     * @return
     */
    List<LesseeCompanyUnit> getLesseeCompanyUnitList(LesseeCompanyUnitQuery query);

    /**
     * 根据条件查询分页查询
     *
     * @param query 查询条件
     * @return
     */
    Result<LesseeCompanyUnitView> getLesseeCompanyUnitViewListWithPage(LesseeCompanyUnitViewQuery query);

    /**
     * 根据条件查询
     *
     * @param query 查询条件
     * @return
     */
    List<LesseeCompanyUnitView> getLesseeCompanyUnitViewList(LesseeCompanyUnitViewQuery query);
}
