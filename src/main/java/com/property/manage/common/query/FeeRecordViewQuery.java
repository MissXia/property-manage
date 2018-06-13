package com.property.manage.common.query;

import com.property.manage.base.model.enums.SortDirection;
import com.property.manage.base.model.model.OrderField;

import java.util.Map;

/**
 * @author 管辉俊
 */
public class FeeRecordViewQuery extends FeeRecordQuery {

    /**==============================批量查询、更新、删除时的Where条件设置==================================**/
    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public FeeRecordViewQuery setItemName(String itemName) {
        this.itemName = itemName;
        this.params.put("itemName", itemName);
        return this;
    }

    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public FeeRecordViewQuery setNickName(String nickName) {
        this.nickName = nickName;
        this.params.put("nickName", nickName);
        return this;
    }

    private String unitNumber;

    public String getUnitNumber() {
        return unitNumber;
    }

    public FeeRecordViewQuery setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
        this.params.put("unitNumber", unitNumber);
        return this;
    }

    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public FeeRecordViewQuery setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.params.put("phoneNumber", phoneNumber);
        return this;
    }

    private String userType;

    public String getUserType() {
        return userType;
    }

    public FeeRecordViewQuery setUserType(String userType) {
        this.userType = userType;
        this.params.put("userType", userType);
        return this;
    }

    /**==============================批量查询时的Order条件顺序设置==================================**/


    public FeeRecordViewQuery orderbyItemName(boolean isAsc) {
        orderFields.add(new OrderField("item_name", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    public FeeRecordViewQuery orderbyNickName(boolean isAsc) {
        orderFields.add(new OrderField("nick_name", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    public FeeRecordViewQuery orderbyUnitNumber(boolean isAsc) {
        orderFields.add(new OrderField("unit_number", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    public FeeRecordViewQuery orderbyPhoneNumber(boolean isAsc) {
        orderFields.add(new OrderField("phone_number", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    public FeeRecordViewQuery orderbyUserType(boolean isAsc) {
        orderFields.add(new OrderField("user_type", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    @Override
    public Map<String, String> getFieldSet() {
        super.getFieldSet();
        fieldMap.put("id", "id");
        fieldMap.put("user_id", "userId");
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
        fieldMap.put("item_name", "itemName");
        fieldMap.put("nick_name", "nickName");
        fieldMap.put("unit_number", "unitNumber");
        fieldMap.put("phone_number", "phoneNumber");
        fieldMap.put("user_type", "userType");
        return fieldMap;
    }
}
