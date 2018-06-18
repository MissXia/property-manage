package com.property.manage.app.model.po.fee.record;


import com.property.manage.base.model.model.BaseParams;

import java.util.Date;

public class FeeRecordTicketParams extends BaseParams {

    /**
     * 记录ID
     */
    private Long recordId;

    /**
     * 开票时间
     */
    private Date ticketTime;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Date getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(Date ticketTime) {
        this.ticketTime = ticketTime;
    }
}