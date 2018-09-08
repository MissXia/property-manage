package com.property.manage.app.service.lessee.company.unit;

import com.property.manage.app.model.po.lessee.company.unit.LesseeCompanyUnitAddParams;
import com.property.manage.app.model.po.lessee.company.unit.LesseeCompanyUnitDelParams;
import com.property.manage.app.model.po.lessee.company.unit.LesseeCompanyUnitListParams;
import com.property.manage.base.model.exception.ParameterException;
import com.property.manage.base.model.model.Result;
import com.property.manage.base.model.utils.CheckUtils;
import com.property.manage.common.enums.UserTypes;
import com.property.manage.common.pojo.LesseeCompanyUnit;
import com.property.manage.common.pojo.LesseeCompanyUnitView;
import com.property.manage.common.pojo.UserInfo;
import com.property.manage.common.query.LesseeCompanyUnitQuery;
import com.property.manage.common.query.LesseeCompanyUnitViewQuery;
import com.property.manage.common.service.LesseeCompanyUnitService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 管辉俊
 * @since 2018-05-22
 */
@Service
public class LesseeCompanyUnitProcessService {

    private static Logger logger = LoggerFactory.getLogger(LesseeCompanyUnitProcessService.class);

    @Resource
    private LesseeCompanyUnitService lesseeCompanyUnitService;

    /**
     * 查询列表
     *
     * @param params
     * @return
     * @throws ParameterException
     */
    public Result<LesseeCompanyUnitView> lesseeCompanyUnitResult(LesseeCompanyUnitListParams params) throws ParameterException {
        // 查询参数
        LesseeCompanyUnitViewQuery query = new LesseeCompanyUnitViewQuery();
        // 企业ID
        query.setCompanyId(params.getCompanyId());
        // 企业名称
        query.setCompanyName(params.getCompanyName());
        // 单元编号
        query.setUnitNumber(params.getUnitNumber());
        // 按创建时间排序
        query.orderbyAddTime(true);
        // 页码
        query.setPage(params.getPage());
        // 显示条数
        query.setPageSize(params.getPageSize());
        // 返回结果
        return lesseeCompanyUnitService.getLesseeCompanyUnitViewListWithPage(query);
    }

    /**
     * 按单元编号查找
     *
     * @param unitNumber
     * @return
     */
    public List<LesseeCompanyUnit> findLesseeCompanyUnitList(Long companyId, String unitNumber) {
        // 查询参数
        LesseeCompanyUnitQuery query = new LesseeCompanyUnitQuery();
        // 企业ID
        query.setCompanyId(companyId);
        // 单元编号
        query.setUnitNumber(unitNumber);
        // 按创建时间排序
        query.orderbyAddTime(true);
        // 返回结果
        return lesseeCompanyUnitService.getLesseeCompanyUnitList(query);
    }

    /**
     * 按单元编号查找
     *
     * @param unitNumber
     * @return
     */
    public LesseeCompanyUnit findLesseeCompanyUnit(Long companyId, String unitNumber) {
        // 按名称查找项目
        List<LesseeCompanyUnit> units = findLesseeCompanyUnitList(companyId, unitNumber);
        // 异常处理
        if (CollectionUtils.isEmpty(units)) {
            // 中断流程
            return null;
        }
        // 返回数据
        return units.get(0);
    }

    /**
     * 新增租户企业的单元编号
     *
     * @param params
     * @throws ParameterException
     */
    public void lesseeCompanyUnitAdd(UserInfo userInfo, LesseeCompanyUnitAddParams params) throws ParameterException {
        // 如果权限小于管理员
        if (userInfo.getUserType() < UserTypes.ADMIN.getKey()) {
            // 中断流程
            throw new ParameterException("您无权进行此操作!");
        }
        // 企业ID
        CheckUtils.ObjectNotNull(params.getCompanyId(), "企业ID", null);
        // 单元编号
        CheckUtils.StringNotBlank(params.getUnitNumber(), "单元编号", null);
        // 按名称查找企业
        List<LesseeCompanyUnit> units = findLesseeCompanyUnitList(params.getCompanyId(), params.getUnitNumber());
        // 异常处理
        if (CollectionUtils.isNotEmpty(units)) {
            // 中断流程
            throw new ParameterException("企业的单元编号重复");
        }
        // 实例化对象
        LesseeCompanyUnit unit = new LesseeCompanyUnit();
        // 企业ID
        unit.setCompanyId(params.getCompanyId());
        // 单元编号
        unit.setUnitNumber(params.getUnitNumber());
        // 创建时间
        unit.setAddTime(new Date());
        // 更新时间
        unit.setUpdTime(new Date());
        // 新增数据
        lesseeCompanyUnitService.addLesseeCompanyUnit(unit);
    }

    /**
     * 删除租户企业的单元编号
     *
     * @param params
     * @throws ParameterException
     */
    public void lesseeCompanyUnitDel(UserInfo userInfo, LesseeCompanyUnitDelParams params) throws ParameterException {
        // 如果权限小于管理员
        if (userInfo.getUserType() < UserTypes.ADMIN.getKey()) {
            // 中断流程
            throw new ParameterException("您无权进行此操作!");
        }
        // 收费项目名称
        CheckUtils.ObjectNotNull(params.getUnitId(), "记录ID", null);
        // 查找对应的项目
        LesseeCompanyUnit unit = lesseeCompanyUnitService.getLesseeCompanyUnitByKey(params.getUnitId());
        // 异常处理
        if (null == unit) {
            // 中断流程
            throw new ParameterException("记录不存在");
        }
        // 删除记录
        lesseeCompanyUnitService.deleteByKey(params.getUnitId());
    }
}