package com.property.manage.base.model.model;

import java.io.Serializable;

public class Request implements Serializable{

    private static final long serialVersionUID = -1299497646593715245L;

    private String apiName;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
}
