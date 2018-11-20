package com.property.manage.app.service.lessee.company;

import com.property.manage.app.model.po.lessee.company.LesseeCompanyAddParams;
import com.property.manage.app.model.po.lessee.company.LesseeCompanyDelParams;
import com.property.manage.app.model.po.lessee.company.LesseeCompanyListParams;
import com.property.manage.app.model.po.lessee.company.LesseeCompanyUpdParams;
import com.property.manage.base.model.exception.ParameterException;
import com.property.manage.base.model.model.Result;
import com.property.manage.base.model.utils.CheckUtils;
import com.property.manage.common.enums.UserTypes;
import com.property.manage.common.pojo.LesseeCompany;
import com.property.manage.common.pojo.LesseeCompanyView;
import com.property.manage.common.pojo.UserInfo;
import com.property.manage.common.query.LesseeCompanyQuery;
import com.property.manage.common.query.LesseeCompanyViewQuery;
import com.property.manage.common.service.FeeRecordService;
import com.property.manage.common.service.LesseeCompanyService;
import com.property.manage.common.service.LesseeCompanyUnitService;
import com.property.manage.common.service.UserInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 管辉俊
 * @since 2018-05-22
 */
@Service
public class LesseeCompanyProcessService {

    private static Logger logger = LoggerFactory.getLogger(LesseeCompanyProcessService.class);

    @Resource
    private LesseeCompanyService lesseeCompanyService;

    @Resource
    private LesseeCompanyUnitService lesseeCompanyUnitService;

    @Autowired
    private FeeRecordService feeRecordService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 查询列表
     *
     * @param params
     * @return
     * @throws ParameterException
     */
    public Result<LesseeCompanyView> lesseeCompanyResult(LesseeCompanyListParams params) throws ParameterException {
        // 查询参数
        LesseeCompanyViewQuery query = new LesseeCompanyViewQuery();
        // 企业名称
        query.setCompanyName(params.getCompanyName());
        // 按创建时间排序
        query.orderbyAddTime(true);
        // 页码
        query.setPage(params.getPage());
        // 显示条数
        query.setPageSize(params.getPageSize());
        // 返回结果
        return lesseeCompanyService.getLesseeCompanyViewListWithPage(query);
    }

    /**
     * 按名称查找企业
     *
     * @param companyName
     * @return
     */
    public List<LesseeCompany> findLesseeCompanyList(String companyName) {
        // 查询参数
        LesseeCompanyQuery query = new LesseeCompanyQuery();
        // 用户类型
        query.setCompanyName(companyName);
        // 按创建时间排序
        query.orderbyAddTime(true);
        // 返回结果
        return lesseeCompanyService.getLesseeCompanyList(query);
    }

    /**
     * 按名称查找企业
     *
     * @param companyName
     * @return
     */
    public LesseeCompany findLesseeCompany(String companyName) {
        // 按名称查找项目
        List<LesseeCompany> companies = findLesseeCompanyList(companyName);
        // 异常处理
        if (CollectionUtils.isEmpty(companies)) {
            // 中断流程
            return null;
        }
        // 返回数据
        return companies.get(0);
    }

    /**
     * 新增租户企业
     *
     * @param params
     * @throws ParameterException
     */
    public void lesseeCompanyAdd(UserInfo userInfo, LesseeCompanyAddParams params) throws ParameterException {
        // 如果权限小于管理员
        if (userInfo.getUserType() < UserTypes.ADMIN.getKey()) {
            // 中断流程
            throw new ParameterException("您无权进行此操作!");
        }
        // 企业名称
        CheckUtils.StringNotBlank(params.getCompanyName(), "企业名称", null);
        // 按名称查找企业
        List<LesseeCompany> companies = findLesseeCompanyList(params.getCompanyName());
        // 异常处理
        if (CollectionUtils.isNotEmpty(companies)) {
            // 中断流程
            throw new ParameterException("企业名称重复");
        }
        // 实例化对象
        LesseeCompany company = new LesseeCompany();
        // 企业名称
        company.setCompanyName(params.getCompanyName());
        // 创建时间
        company.setAddTime(new Date());
        // 更新时间
        company.setUpdTime(new Date());
        // 新增数据
        lesseeCompanyService.addLesseeCompany(company);
    }

    /**
     * 更新租户企业
     *
     * @param params
     * @throws ParameterException
     */
    public void lesseeCompanyUpd(UserInfo userInfo, LesseeCompanyUpdParams params) throws ParameterException {
        // 如果权限小于管理员
        if (userInfo.getUserType() < UserTypes.ADMIN.getKey()) {
            // 中断流程
            throw new ParameterException("您无权进行此操作!");
        }
        // 收费项目ID
        CheckUtils.ObjectNotNull(params.getCompanyId(), "企业ID", null);
        // 企业名称
        CheckUtils.StringNotBlank(params.getCompanyName(), "企业名称", null);
        // 查找对应的项目
        LesseeCompany company = lesseeCompanyService.getLesseeCompanyByKey(params.getCompanyId());
        // 异常处理
        if (null == company) {
            // 中断流程
            throw new ParameterException("租户企业不存在");
        }
        // 按名称查找项目
        LesseeCompany name = findLesseeCompany(params.getCompanyName());
        // 如果不是同一条数据
        if (null != name && !name.getId().equals(company.getId())) {
            // 中断流程
            throw new ParameterException("企业名称重复");
        }
        // 企业名称
        company.setCompanyName(params.getCompanyName());
        // 更新时间
        company.setUpdTime(new Date());
        // 更新数据
        lesseeCompanyService.updateLesseeCompanyByKey(company);
    }

    /**
     * 删除租户企业
     *
     * @param params
     * @throws ParameterException
     */
    public void lesseeCompanyDel(UserInfo userInfo, LesseeCompanyDelParams params) throws ParameterException {
        // 如果权限小于管理员
        if (userInfo.getUserType() < UserTypes.ADMIN.getKey()) {
            // 中断流程
            throw new ParameterException("您无权进行此操作!");
        }
        // 收费项目名称
        CheckUtils.ObjectNotNull(params.getCompanyId(), "企业ID", null);
        // 查找对应的项目
        LesseeCompany company = lesseeCompanyService.getLesseeCompanyByKey(params.getCompanyId());
        // 异常处理
        if (null == company) {
            // 中断流程
            throw new ParameterException("租户企业不存在");
        }
        // 删除企业关联记录
        feeRecordService.deleteByCompany(params.getCompanyId());
        // 查找企业下的单元编号
        lesseeCompanyUnitService.deleteByCompany(params.getCompanyId());
        // 删除企业下所有关联人
        userInfoService.deleteByCompany(params.getCompanyId());
        // 删除企业
        lesseeCompanyService.deleteByKey(params.getCompanyId());
    }
}