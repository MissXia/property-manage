package com.property.manage.app.controller;

import com.property.manage.app.model.po.fee.record.*;
import com.property.manage.app.service.fee.record.FeeRecordProcessService;
import com.property.manage.base.controller.BaseController;
import com.property.manage.base.model.exception.ParameterException;
import com.property.manage.base.model.exception.SessionException;
import com.property.manage.base.model.model.Response;
import com.property.manage.common.pojo.UserInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/fee/record")
public class FeeRecordController extends BaseController {

    @Resource
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
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public Response detail(@RequestBody FeeRecordDetailParams params) throws ParameterException, SessionException {
        // 取得Session中的用户
        UserInfo userInfo = sessionService.getUserInfo();
        // 返回数据
        return Response.success(feeRecordProcessService.feeRecordDetail(userInfo, params));
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

    @ResponseBody
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public Response pay(@RequestBody FeeRecordPayParams params) throws ParameterException, SessionException {
        // 取得Session中的用户
        UserInfo userInfo = sessionService.getUserInfo();
        // 审核用户
        feeRecordProcessService.feeRecordPay(userInfo, params);
        // 返回数据
        return Response.success();
    }

    @ResponseBody
    @RequestMapping(value = "/ticket", method = RequestMethod.POST)
    public Response ticket(@RequestBody FeeRecordTicketParams params) throws ParameterException, SessionException {
        // 取得Session中的用户
        UserInfo userInfo = sessionService.getUserInfo();
        // 审核用户
        feeRecordProcessService.feeRecordTicket(userInfo, params);
        // 返回数据
        return Response.success();
    }

    @RequestMapping(value = "/upload/template", method = RequestMethod.POST)
    public void uploadTemplate(HttpServletResponse rsp) throws ParameterException, SessionException {
        // 初始化模块
        feeRecordProcessService.uploadTemplate(rsp);
    }

    @ResponseBody
    @RequestMapping(value = "/upload/excel", method = RequestMethod.POST)
    public Response uploadExcel(@RequestParam("file") MultipartFile file) throws ParameterException, SessionException {
        // 导入数据
        return Response.success(feeRecordProcessService.upload(file));
    }

    @RequestMapping(value = "/upload/error", method = RequestMethod.POST)
    public void uploadError(HttpServletResponse rsp) throws ParameterException, SessionException {
        // 初始化模块
        feeRecordProcessService.uploadError(rsp);
    }

    @ResponseBody
    @RequestMapping(value = "/upload/record", method = RequestMethod.POST)
    public Response uploadRecord() throws ParameterException, SessionException {
        // 导入数据
        return Response.success(feeRecordProcessService.uploadRecord());
    }
}