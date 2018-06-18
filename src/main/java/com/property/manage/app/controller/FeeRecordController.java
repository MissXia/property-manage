package com.property.manage.app.controller;

import com.property.manage.app.model.po.fee.record.FeeRecordAddParams;
import com.property.manage.app.model.po.fee.record.FeeRecordDelParams;
import com.property.manage.app.model.po.fee.record.FeeRecordListParams;
import com.property.manage.app.model.po.fee.record.FeeRecordUpdParams;
import com.property.manage.app.service.fee.record.FeeRecordProcessService;
import com.property.manage.base.controller.BaseController;
import com.property.manage.base.model.exception.ParameterException;
import com.property.manage.base.model.exception.SessionException;
import com.property.manage.base.model.model.Response;
import com.property.manage.common.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fee/record")
public class FeeRecordController extends BaseController {

    @Autowired
    private FeeRecordProcessService feeRecordProcessService;

    @ResponseBody
    @RequestMapping(value = "/owner/list", method = RequestMethod.POST)
    public Response ownerList(@RequestBody FeeRecordListParams params) throws ParameterException, SessionException {
        // 取得Session中的用户
        UserInfo userInfo = sessionService.getUserInfo();
        // 返回数据
        return Response.success(feeRecordProcessService.ownerFeeRecordResult(userInfo, params));
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Response list(@RequestBody FeeRecordListParams params) throws ParameterException, SessionException {
        // 返回数据
        return Response.success(feeRecordProcessService.feeRecordResult(params));
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response add(@RequestBody FeeRecordAddParams params) throws ParameterException, SessionException {
        // 取得Session中的用户
        UserInfo userInfo = sessionService.getUserInfo();
        // 审核用户
        feeRecordProcessService.feeReocrdAdd(userInfo, params);
        // 返回数据
        return Response.success();
    }

    @ResponseBody
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public Response upd(@RequestBody FeeRecordUpdParams params) throws ParameterException, SessionException {
        // 取得Session中的用户
        UserInfo userInfo = sessionService.getUserInfo();
        // 审核用户
        feeRecordProcessService.feeRecordUpd(userInfo, params);
        // 返回数据
        return Response.success();
    }

    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public Response del(@RequestBody FeeRecordDelParams params) throws ParameterException, SessionException {
        // 取得Session中的用户
        UserInfo userInfo = sessionService.getUserInfo();
        // 审核用户
        feeRecordProcessService.feeRecordDel(userInfo, params);
        // 返回数据
        return Response.success();
    }
}