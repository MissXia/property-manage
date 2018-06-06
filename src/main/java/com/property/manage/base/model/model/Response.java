package com.property.manage.base.model.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.property.manage.base.model.constants.ResponseCode;
import com.property.manage.base.model.enums.AlertLevel;
import com.property.manage.base.model.enums.AlertType;

import java.io.Serializable;

/**
 * @author guozhenbin
 * @since 17/2/5.
 */
public class Response implements Serializable {

    private static final long serialVersionUID = -3039113299834206219L;

    private static Response response = new Response();

    /**
     * api 名称
     */
    @JSONField(name = "apiName")
    private String apiName;

    /**
     * api响应代码
     */
    private int result = ResponseCode.CODE_SUCCESS;

    /**
     * 响应信息（如失败出错描述等）
     */
    private String message = "操作成功";

    /**
     * 响应实体信息
     */
    private Object data;

    public Response() {

    }

    public Response(Request request) {
        this.apiName = request == null ? "unKnown" : request.getApiName();
    }

    public static Response newInstance() {
        return new Response();
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 操作成功.并且无返回结果
     *
     * @return
     */
    public static Response success() {
        response.setMessage("操作成功");
        response.setResult(ResponseCode.CODE_SUCCESS);
        response.setData(null);
        return response;
    }

    /**
     * 操作成功，有返回结果
     *
     * @param obj
     * @return
     */
    public static Response success(Object obj) {
        response.setMessage("操作成功");
        response.setResult(ResponseCode.CODE_SUCCESS);
        response.setData(obj);
        return response;
    }

    /**
     * 有弹窗提示
     *
     * @param obj
     * @param message
     * @param alertLevel
     * @param alertType
     * @return
     */
    public static Response dailog(Object obj, String message, AlertLevel alertLevel, AlertType alertType) {
        response.setMessage(message);
        response.setResult(ResponseCode.CODE_SUCCESS);
        response.setData(new ResponseAlert(message, obj, alertLevel, alertType));
        return response;
    }

    /**
     * 操作成功，并且无返回结果
     *
     * @param message
     * @return
     */
    public static Response successDailog(String message) {
        return dailog(null, message, AlertLevel.SUCCESS, AlertType.DAILOG);
    }

    /**
     * 操作成功，并且无返回结果
     *
     * @param message
     * @return
     */
    public static Response faildDailog(String message) {
        return dailog(null, message, AlertLevel.FAILD, AlertType.DAILOG);
    }

    /**
     * 操作成功，并且无返回结果
     *
     * @param message
     * @return
     */
    public static Response warningDailog(String message) {
        return dailog(null, message, AlertLevel.WARNING, AlertType.DAILOG);
    }

    /**
     * 操作失败
     *
     * @param msg       失败提示
     * @param errorType 导致失败的原因
     * @return
     */
    public static Response error(String msg, int errorType) {
        response.setMessage(msg);
        response.setResult(errorType);
        response.setData(null);
        return response;
    }
}
