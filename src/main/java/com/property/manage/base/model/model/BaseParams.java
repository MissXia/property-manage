package com.property.manage.base.model.model;

import java.io.Serializable;


public class BaseParams extends Request {

    /**
     * 当前页码
     */
    private Integer page = 1;

    /**
     * 每页显示条数
     */
    private Integer pageSize = 20;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}