package com.property.manage.app.controller;

import com.property.manage.app.model.po.UserListParams;
import com.property.manage.app.model.po.UserLoginParams;
import com.property.manage.app.service.UserLoginService;
import com.property.manage.base.controller.BaseController;
import com.property.manage.base.model.exception.ParameterException;
import com.property.manage.base.model.exception.SessionException;
import com.property.manage.base.model.model.Response;
import com.property.manage.common.pojo.UserInfo;
import com.property.manage.common.query.UserInfoQuery;
import com.property.manage.common.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserLoginService userLoginService;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Response list(@RequestBody UserListParams params) throws ParameterException, SessionException {
        // 取得Session中的用户
        UserInfo userInfo = sessionService.getUserInfo();
        // 返回数据
        return Response.success(userLoginService.userInfoResult(userInfo, params));
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(HttpServletRequest request, @RequestBody UserLoginParams params) throws ParameterException {
        // 登录操作
        UserInfo info = userLoginService.login(params);
        // 取得SessionId
        info.setSessionId(request.getSession().getId());
        // 返回数据
        return Response.success(info);
    }

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Response query(@RequestParam(name = "openId") String openId) {
        UserInfoQuery query = new UserInfoQuery();
        query.setOpenId(openId);
        return Response.success(userInfoService.getUserInfoList(query));
    }
}