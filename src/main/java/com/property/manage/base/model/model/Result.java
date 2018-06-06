package com.property.manage.base.model.model;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = 5907686226047745734L;

    private boolean isSuccess = true;

    private List<T> list;

    private long count;

    private String errorMsg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "isSuccess=" + isSuccess +
                ", list=" + JSON.toJSONString(list) +
                ", count=" + count +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
