package com.property.manage.common.service;

import com.property.manage.base.model.model.Result;
import com.property.manage.common.pojo.LesseeCompany;
import com.property.manage.common.query.LesseeCompanyQuery;

import java.util.List;

/**
 * @author 管辉俊
 * @since 2018-08-20
 */
public interface LesseeCompanyService {

    /**
     * 基本插入
     *
     * @return
     */
    Long addLesseeCompany(LesseeCompany record);

    /**
     * 根据主键查询
     */
    LesseeCompany getLesseeCompanyByKey(Long id);

    /**
     * 根据主键删除
     *
     * @return
     */
    Integer deleteByKey(Long id);

    /**
     * 根据主键更新
     *
     * @return
     */
    Integer updateLesseeCompanyByKey(LesseeCompany record);

    /**
     * 根据条件查询分页查询
     *
     * @param query 查询条件
     * @return
     */
    Result<LesseeCompany> getLesseeCompanyListWithPage(LesseeCompanyQuery query);

    /**
     * 根据条件查询
     *
     * @param query 查询条件
     * @return
     */
    List<LesseeCompany> getLesseeCompanyList(LesseeCompanyQuery query);
}
