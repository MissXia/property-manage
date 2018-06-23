package com.property.manage.base.model.model;

import java.io.Serializable;

public class Request implements Serializable{

    private static final long serialVersionUID = -1299497646593715245L;

    private String requestId;

    private String apiName;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
}