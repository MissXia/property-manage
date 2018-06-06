package com.property.manage.base.model.model;


import com.property.manage.base.model.enums.AlertLevel;
import com.property.manage.base.model.enums.AlertType;

import java.io.Serializable;

/**
 * @author guozhenbin
 * @since 17/2/5.
 */
public class ResponseAlert implements Serializable {

    private static final long serialVersionUID = -3039113299834206219L;

    /**
     * 响应信息（如失败出错描述等）
     */
    private String message = "操作成功";

    /**
     * 响应实体信息
     */
    private Object data;

    /**
     * 提示级别
     */
    private AlertLevel alertLevel;

    /**
     * 对话框类型
     */
    private AlertType alertType;

    public ResponseAlert() {

    }

    public ResponseAlert(String message, Object data, AlertLevel alertLevel, AlertType alertType) {
        this.message = message;
        this.data = data;
        this.alertLevel = alertLevel;
        this.alertType = alertType;
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

    public AlertLevel getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(AlertLevel alertLevel) {
        this.alertLevel = alertLevel;
    }

    public AlertType getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

}
