package com.property.manage.app.controller;

import com.property.manage.app.model.po.lessee.company.LesseeCompanyAddParams;
import com.property.manage.app.model.po.lessee.company.LesseeCompanyDelParams;
import com.property.manage.app.model.po.lessee.company.LesseeCompanyListParams;
import com.property.manage.app.model.po.lessee.company.LesseeCompanyUpdParams;
import com.property.manage.app.service.lessee.company.LesseeCompanyProcessService;
import com.property.manage.base.controller.BaseController;
import com.property.manage.base.model.exception.ParameterException;
import com.property.manage.base.model.exception.SessionException;
import com.property.manage.base.model.model.Response;
import com.property.manage.common.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessee/company")
public class LesseeCompanyController extends BaseController {

    @Autowired
    private LesseeCompanyProcessService lesseeCompanyProcessService;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Response list(@RequestBody LesseeCompanyListParams params) throws ParameterException, SessionException {
        // 返回数据
        return Response.success(lesseeCompanyProcessService.lesseeCompanyResult(params));
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response add(@RequestBody LesseeCompanyAddParams params) throws ParameterException, SessionException {
        // 取得Session中的用户
        UserInfo userInfo = sessionService.getUserInfo();
        // 新增
        lesseeCompanyProcessService.lesseeCompanyAdd(userInfo, params);
        // 返回数据
        return Response.success();
    }

    @ResponseBody
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public Response upd(@RequestBody LesseeCompanyUpdParams params) throws ParameterException, SessionException {
        // 取得Session中的用户
        UserInfo userInfo = sessionService.getUserInfo();
        // 更新
        lesseeCompanyProcessService.lesseeCompanyUpd(userInfo, params);
        // 返回数据
        return Response.success();
    }

    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Response del(@RequestBody LesseeCompanyDelParams params) throws ParameterException, SessionException {
        // 取得Session中的用户
        UserInfo userInfo = sessionService.getUserInfo();
        // 删除
        lesseeCompanyProcessService.lesseeCompanyDel(userInfo, params);
        // 返回数据
        return Response.success();
    }
}