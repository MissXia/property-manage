package com.property.manage.common.query;

import com.property.manage.base.model.enums.SortDirection;
import com.property.manage.base.model.model.OrderField;
import com.property.manage.base.model.query.BaseQuery;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author 管辉俊
 */
public class FeeRecordQuery extends BaseQuery {

    /**==============================批量查询、更新、删除时的Where条件设置==================================**/
    /**
     * 自增主键
     **/
    private Long id;

    public Long getId() {
        return id;
    }

    public FeeRecordQuery setId(Long id) {
        this.id = id;
        this.params.put("id", id);
        return this;
    }

    /**
     * 企业ID
     **/
    private Long companyId;

    public Long getCompanyId() {
        return companyId;
    }

    public FeeRecordQuery setCompanyId(Long companyId) {
        this.companyId = companyId;
        this.params.put("companyId", companyId);
        return this;
    }

    /**
     * 收费项目ID
     **/
    private Long itemId;

    public Long getItemId() {
        return itemId;
    }

    public FeeRecordQuery setItemId(Long itemId) {
        this.itemId = itemId;
        this.params.put("itemId", itemId);
        return this;
    }

    /**
     * 所属月份
     **/
    private String theMonth;

    public String getTheMonth() {
        return theMonth;
    }

    public FeeRecordQuery setTheMonth(String theMonth) {
        this.theMonth = theMonth;
        this.params.put("theMonth", theMonth);
        return this;
    }

    /**
     * 应收金额
     **/
    private BigDecimal planPayFee;

    public BigDecimal getPlanPayFee() {
        return planPayFee;
    }

    public FeeRecordQuery setPlanPayFee(BigDecimal planPayFee) {
        this.planPayFee = planPayFee;
        this.params.put("planPayFee", planPayFee);
        return this;
    }

    /**
     * 实收金额
     **/
    private BigDecimal realPayFee;

    public BigDecimal getRealPayFee() {
        return realPayFee;
    }

    public FeeRecordQuery setRealPayFee(BigDecimal realPayFee) {
        this.realPayFee = realPayFee;
        this.params.put("realPayFee", realPayFee);
        return this;
    }

    /**
     * 缴费时间
     **/
    private Date payTimeStart;

    public Date getPayTimeStart() {
        return payTimeStart;
    }

    public FeeRecordQuery setPayTimeStart(Date payTime) {
        this.payTimeStart = payTime;
        this.params.put("payTimeStart", payTime);
        return this;
    }

    private Date payTimeEnd;

    public Date getPayTimeEnd() {
        return payTimeEnd;
    }

    public FeeRecordQuery setPayTimeEnd(Date payTime) {
        this.payTimeEnd = payTime;
        this.params.put("payTimeEnd", payTime);
        return this;
    }

    private Date payTimeEqual;

    public Date getPayTimeEqual() {
        return payTimeEqual;
    }

    public FeeRecordQuery setPayTimeEqual(Date payTime) {
        this.payTimeEqual = payTime;
        this.params.put("payTimeEqual", payTime);
        return this;
    }

    /**
     * 缴费状态：0、未缴费 1、已缴费
     **/
    private Integer payStatus;

    public Integer getPayStatus() {
        return payStatus;
    }

    public FeeRecordQuery setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
        this.params.put("payStatus", payStatus);
        return this;
    }

    /**
     * 开票时间
     **/
    private Date ticketTimeStart;

    public Date getTicketTimeStart() {
        return ticketTimeStart;
    }

    public FeeRecordQuery setTicketTimeStart(Date ticketTime) {
        this.ticketTimeStart = ticketTime;
        this.params.put("ticketTimeStart", ticketTime);
        return this;
    }

    private Date ticketTimeEnd;

    public Date getTicketTimeEnd() {
        return ticketTimeEnd;
    }

    public FeeRecordQuery setTicketTimeEnd(Date ticketTime) {
        this.ticketTimeEnd = ticketTime;
        this.params.put("ticketTimeEnd", ticketTime);
        return this;
    }

    private Date ticketTimeEqual;

    public Date getTicketTimeEqual() {
        return ticketTimeEqual;
    }

    public FeeRecordQuery setTicketTimeEqual(Date ticketTime) {
        this.ticketTimeEqual = ticketTime;
        this.params.put("ticketTimeEqual", ticketTime);
        return this;
    }

    /**
     * 开票状态：0、未开票 1、已开票
     **/
    private Integer ticketStatus;

    public Integer getTicketStatus() {
        return ticketStatus;
    }

    public FeeRecordQuery setTicketStatus(Integer ticketStatus) {
        this.ticketStatus = ticketStatus;
        this.params.put("ticketStatus", ticketStatus);
        return this;
    }

    /**
     * 创建时间
     **/
    private Date addTimeStart;

    public Date getAddTimeStart() {
        return addTimeStart;
    }

    public FeeRecordQuery setAddTimeStart(Date addTime) {
        this.addTimeStart = addTime;
        this.params.put("addTimeStart", addTime);
        return this;
    }

    private Date addTimeEnd;

    public Date getAddTimeEnd() {
        return addTimeEnd;
    }

    public FeeRecordQuery setAddTimeEnd(Date addTime) {
        this.addTimeEnd = addTime;
        this.params.put("addTimeEnd", addTime);
        return this;
    }

    private Date addTimeEqual;

    public Date getAddTimeEqual() {
        return addTimeEqual;
    }

    public FeeRecordQuery setAddTimeEqual(Date addTime) {
        this.addTimeEqual = addTime;
        this.params.put("addTimeEqual", addTime);
        return this;
    }

    /**
     * 修改时间
     **/
    private Date updTimeStart;

    public Date getUpdTimeStart() {
        return updTimeStart;
    }

    public FeeRecordQuery setUpdTimeStart(Date updTime) {
        this.updTimeStart = updTime;
        this.params.put("updTimeStart", updTime);
        return this;
    }

    private Date updTimeEnd;

    public Date getUpdTimeEnd() {
        return updTimeEnd;
    }

    public FeeRecordQuery setUpdTimeEnd(Date updTime) {
        this.updTimeEnd = updTime;
        this.params.put("updTimeEnd", updTime);
        return this;
    }

    private Date updTimeEqual;

    public Date getUpdTimeEqual() {
        return updTimeEqual;
    }

    public FeeRecordQuery setUpdTimeEqual(Date updTime) {
        this.updTimeEqual = updTime;
        this.params.put("updTimeEqual", updTime);
        return this;
    }
    /**==============================批量查询时的Order条件顺序设置==================================**/
    /**
     * 设置排序按属性：自增主键
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordQuery orderbyId(boolean isAsc) {
        orderFields.add(new OrderField("id", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：企业ID
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordQuery orderbyCompanyId(boolean isAsc) {
        orderFields.add(new OrderField("company_id", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：收费项目ID
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordQuery orderbyItemId(boolean isAsc) {
        orderFields.add(new OrderField("item_id", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：所属月份
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordQuery orderbyTheMonth(boolean isAsc) {
        orderFields.add(new OrderField("the_month", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：应收金额
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordQuery orderbyPlanPayFee(boolean isAsc) {
        orderFields.add(new OrderField("plan_pay_fee", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：实收金额
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordQuery orderbyRealPayFee(boolean isAsc) {
        orderFields.add(new OrderField("real_pay_fee", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：缴费时间
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordQuery orderbyPayTime(boolean isAsc) {
        orderFields.add(new OrderField("pay_time", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：缴费状态：0、未缴费 1、已缴费
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordQuery orderbyPayStatus(boolean isAsc) {
        orderFields.add(new OrderField("pay_status", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：开票时间
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordQuery orderbyTicketTime(boolean isAsc) {
        orderFields.add(new OrderField("ticket_time", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：开票状态：0、未开票 1、已开票
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordQuery orderbyTicketStatus(boolean isAsc) {
        orderFields.add(new OrderField("ticket_status", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：创建时间
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordQuery orderbyAddTime(boolean isAsc) {
        orderFields.add(new OrderField("add_time", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：修改时间
     *
     * @param isAsc 是否升序，否则为降序
     */
    public FeeRecordQuery orderbyUpdTime(boolean isAsc) {
        orderFields.add(new OrderField("upd_time", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    @Override
    public Map<String, String> getFieldSet() {
        super.getFieldSet();
        fieldMap.put("id", "id");
        fieldMap.put("company_id", "companyId");
        fieldMap.put("item_id", "itemId");
        fieldMap.put("the_month", "theMonth");
        fieldMap.put("plan_pay_fee", "planPayFee");
        fieldMap.put("real_pay_fee", "realPayFee");
        fieldMap.put("pay_time", "payTime");
        fieldMap.put("pay_status", "payStatus");
        fieldMap.put("ticket_time", "ticketTime");
        fieldMap.put("ticket_status", "ticketStatus");
        fieldMap.put("add_time", "addTime");
        fieldMap.put("upd_time", "updTime");
        return fieldMap;
    }
}
