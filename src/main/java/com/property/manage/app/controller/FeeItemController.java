package com.property.manage.app.controller;

import com.property.manage.app.model.po.fee.item.FeeItemAddParams;
import com.property.manage.app.model.po.fee.item.FeeItemDelParams;
import com.property.manage.app.model.po.fee.item.FeeItemListParams;
import com.property.manage.app.model.po.fee.item.FeeItemUpdParams;
import com.property.manage.app.service.fee.item.FeeItemProcessService;
import com.property.manage.base.controller.BaseController;
import com.property.manage.base.model.exception.ParameterException;
import com.property.manage.base.model.exception.SessionException;
import com.property.manage.base.model.model.Response;
import com.property.manage.common.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fee/item")
public class FeeItemController extends BaseController {

    @Autowired
    private FeeItemProcessService feeItemProcessService;

    @ResponseBody
    @RequestMapping(value = "/list" , method = RequestMethod.POST)
    public Response list(@RequestBody FeeItemListParams params) throws ParameterException, SessionException {
        // 返回数据
        return Response.success(feeItemProcessService.feeItemResult(params));
    }

    @ResponseBody
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public Response add(@RequestBody FeeItemAddParams params) throws ParameterException, SessionException {
        // 取得Session中的用户
        UserInfo userInfo = sessionService.getUserInfo();
        // 审核用户
        feeItemProcessService.feeItemAdd(userInfo, params);
        // 返回数据
        return Response.success();
    }

    @ResponseBody
    @RequestMapping(value = "/upd" , method = RequestMethod.POST)
    public Response upd(@RequestBody FeeItemUpdParams params) throws ParameterException, SessionException {
        // 取得Session中的用户
        UserInfo userInfo = sessionService.getUserInfo();
        // 审核用户
        feeItemProcessService.feeItemUpd(userInfo, params);
        // 返回数据
        return Response.success();
    }

    @ResponseBody
    @RequestMapping(value = "/del" , method = RequestMethod.POST)
    public Response del(@RequestBody FeeItemDelParams params) throws ParameterException, SessionException {
        // 取得Session中的用户
        UserInfo userInfo = sessionService.getUserInfo();
        // 审核用户
        feeItemProcessService.feeItemDel(userInfo, params);
        // 返回数据
        return Response.success();
    }
}