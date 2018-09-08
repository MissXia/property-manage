package com.property.manage.app.controller;

import com.property.manage.app.model.po.lessee.company.unit.LesseeCompanyUnitAddParams;
import com.property.manage.app.model.po.lessee.company.unit.LesseeCompanyUnitDelParams;
import com.property.manage.app.model.po.lessee.company.unit.LesseeCompanyUnitListParams;
import com.property.manage.app.service.lessee.company.unit.LesseeCompanyUnitProcessService;
import com.property.manage.base.controller.BaseController;
import com.property.manage.base.model.exception.ParameterException;
import com.property.manage.base.model.exception.SessionException;
import com.property.manage.base.model.model.Response;
import com.property.manage.common.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessee/company/unit")
public class LesseeCompanyUnitController extends BaseController {

    @Autowired
    private LesseeCompanyUnitProcessService lesseeCompanyUnitProcessService;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Response list(@RequestBody LesseeCompanyUnitListParams params) throws ParameterException, SessionException {
        // 返回数据
        return Response.success(lesseeCompanyUnitProcessService.lesseeCompanyUnitResult(params));
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response add(@RequestBody LesseeCompanyUnitAddParams params) throws ParameterException, SessionException {
        // 取得Session中的用户
        UserInfo userInfo = sessionService.getUserInfo();
        // 新增
        lesseeCompanyUnitProcessService.lesseeCompanyUnitAdd(userInfo, params);
        // 返回数据
        return Response.success();
    }

    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Response del(@RequestBody LesseeCompanyUnitDelParams params) throws ParameterException, SessionException {
        // 取得Session中的用户
        UserInfo userInfo = sessionService.getUserInfo();
        // 删除
        lesseeCompanyUnitProcessService.lesseeCompanyUnitDel(userInfo, params);
        // 返回数据
        return Response.success();
    }
}