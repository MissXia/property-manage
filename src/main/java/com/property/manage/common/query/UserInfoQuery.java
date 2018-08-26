package com.property.manage.common.query;

import com.property.manage.base.model.enums.SortDirection;
import com.property.manage.base.model.model.OrderField;
import com.property.manage.base.model.query.BaseQuery;

import java.util.Date;
import java.util.Map;

/**
 * @author 管辉俊
 */
public class UserInfoQuery extends BaseQuery {

    /**==============================批量查询、更新、删除时的Where条件设置==================================**/
    /**
     * 自增主键
     **/
    private Long id;

    public Long getId() {
        return id;
    }

    public UserInfoQuery setId(Long id) {
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

    public UserInfoQuery setCompanyId(Long companyId) {
        this.companyId = companyId;
        this.params.put("companyId", companyId);
        return this;
    }

    /**
     * 用户昵称
     **/
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public UserInfoQuery setNickName(String nickName) {
        this.nickName = nickName;
        this.params.put("nickName", nickName);
        return this;
    }

    /**
     * 手机号码
     **/
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserInfoQuery setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.params.put("phoneNumber", phoneNumber);
        return this;
    }

    /**
     * 用户类型：0、未知 1、普通用户 7、普通员工 8、管理员 9、超级管理员
     **/
    private Integer userType;

    public Integer getUserType() {
        return userType;
    }

    public UserInfoQuery setUserType(Integer userType) {
        this.userType = userType;
        this.params.put("userType", userType);
        return this;
    }

    /**
     * 用户头像
     **/
    private String avatarUrl;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public UserInfoQuery setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        this.params.put("avatarUrl", avatarUrl);
        return this;
    }

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     **/
    private Integer gender;

    public Integer getGender() {
        return gender;
    }

    public UserInfoQuery setGender(Integer gender) {
        this.gender = gender;
        this.params.put("gender", gender);
        return this;
    }

    /**
     * 用户唯一标识
     **/
    private String openId;

    public String getOpenId() {
        return openId;
    }

    public UserInfoQuery setOpenId(String openId) {
        this.openId = openId;
        this.params.put("openId", openId);
        return this;
    }

    /**
     * 会话密钥
     **/
    private String sessionKey;

    public String getSessionKey() {
        return sessionKey;
    }

    public UserInfoQuery setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
        this.params.put("sessionKey", sessionKey);
        return this;
    }

    /**
     * 用户所在城市
     **/
    private String city;

    public String getCity() {
        return city;
    }

    public UserInfoQuery setCity(String city) {
        this.city = city;
        this.params.put("city", city);
        return this;
    }

    /**
     * 用户所在省份
     **/
    private String province;

    public String getProvince() {
        return province;
    }

    public UserInfoQuery setProvince(String province) {
        this.province = province;
        this.params.put("province", province);
        return this;
    }

    /**
     * 用户所在国家
     **/
    private String country;

    public String getCountry() {
        return country;
    }

    public UserInfoQuery setCountry(String country) {
        this.country = country;
        this.params.put("country", country);
        return this;
    }

    /**
     * 用户的语言，简体中文为zh_CN
     **/
    private String language;

    public String getLanguage() {
        return language;
    }

    public UserInfoQuery setLanguage(String language) {
        this.language = language;
        this.params.put("language", language);
        return this;
    }

    /**
     * 创建时间
     **/
    private Date addTimeStart;

    public Date getAddTimeStart() {
        return addTimeStart;
    }

    public UserInfoQuery setAddTimeStart(Date addTime) {
        this.addTimeStart = addTime;
        this.params.put("addTimeStart", addTime);
        return this;
    }

    private Date addTimeEnd;

    public Date getAddTimeEnd() {
        return addTimeEnd;
    }

    public UserInfoQuery setAddTimeEnd(Date addTime) {
        this.addTimeEnd = addTime;
        this.params.put("addTimeEnd", addTime);
        return this;
    }

    private Date addTimeEqual;

    public Date getAddTimeEqual() {
        return addTimeEqual;
    }

    public UserInfoQuery setAddTimeEqual(Date addTime) {
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

    public UserInfoQuery setUpdTimeStart(Date updTime) {
        this.updTimeStart = updTime;
        this.params.put("updTimeStart", updTime);
        return this;
    }

    private Date updTimeEnd;

    public Date getUpdTimeEnd() {
        return updTimeEnd;
    }

    public UserInfoQuery setUpdTimeEnd(Date updTime) {
        this.updTimeEnd = updTime;
        this.params.put("updTimeEnd", updTime);
        return this;
    }

    private Date updTimeEqual;

    public Date getUpdTimeEqual() {
        return updTimeEqual;
    }

    public UserInfoQuery setUpdTimeEqual(Date updTime) {
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
    public UserInfoQuery orderbyId(boolean isAsc) {
        orderFields.add(new OrderField("id", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：企业ID
     *
     * @param isAsc 是否升序，否则为降序
     */
    public UserInfoQuery orderbyCompanyId(boolean isAsc) {
        orderFields.add(new OrderField("company_id", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：用户昵称
     *
     * @param isAsc 是否升序，否则为降序
     */
    public UserInfoQuery orderbyNickName(boolean isAsc) {
        orderFields.add(new OrderField("nick_name", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：手机号码
     *
     * @param isAsc 是否升序，否则为降序
     */
    public UserInfoQuery orderbyPhoneNumber(boolean isAsc) {
        orderFields.add(new OrderField("phone_number", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：用户类型：0、未知 1、普通用户 7、普通员工 8、管理员 9、超级管理员
     *
     * @param isAsc 是否升序，否则为降序
     */
    public UserInfoQuery orderbyUserType(boolean isAsc) {
        orderFields.add(new OrderField("user_type", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：用户头像
     *
     * @param isAsc 是否升序，否则为降序
     */
    public UserInfoQuery orderbyAvatarUrl(boolean isAsc) {
        orderFields.add(new OrderField("avatar_url", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     *
     * @param isAsc 是否升序，否则为降序
     */
    public UserInfoQuery orderbyGender(boolean isAsc) {
        orderFields.add(new OrderField("gender", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：用户唯一标识
     *
     * @param isAsc 是否升序，否则为降序
     */
    public UserInfoQuery orderbyOpenId(boolean isAsc) {
        orderFields.add(new OrderField("open_id", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：会话密钥
     *
     * @param isAsc 是否升序，否则为降序
     */
    public UserInfoQuery orderbySessionKey(boolean isAsc) {
        orderFields.add(new OrderField("session_key", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：用户所在城市
     *
     * @param isAsc 是否升序，否则为降序
     */
    public UserInfoQuery orderbyCity(boolean isAsc) {
        orderFields.add(new OrderField("city", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：用户所在省份
     *
     * @param isAsc 是否升序，否则为降序
     */
    public UserInfoQuery orderbyProvince(boolean isAsc) {
        orderFields.add(new OrderField("province", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：用户所在国家
     *
     * @param isAsc 是否升序，否则为降序
     */
    public UserInfoQuery orderbyCountry(boolean isAsc) {
        orderFields.add(new OrderField("country", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：用户的语言，简体中文为zh_CN
     *
     * @param isAsc 是否升序，否则为降序
     */
    public UserInfoQuery orderbyLanguage(boolean isAsc) {
        orderFields.add(new OrderField("language", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：创建时间
     *
     * @param isAsc 是否升序，否则为降序
     */
    public UserInfoQuery orderbyAddTime(boolean isAsc) {
        orderFields.add(new OrderField("add_time", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    /**
     * 设置排序按属性：修改时间
     *
     * @param isAsc 是否升序，否则为降序
     */
    public UserInfoQuery orderbyUpdTime(boolean isAsc) {
        orderFields.add(new OrderField("upd_time", isAsc ? SortDirection.ASC : SortDirection.DESC));
        return this;
    }

    @Override
    public Map<String, String> getFieldSet() {
        super.getFieldSet();
        fieldMap.put("id", "id");
        fieldMap.put("company_id", "companyId");
        fieldMap.put("nick_name", "nickName");
        fieldMap.put("phone_number", "phoneNumber");
        fieldMap.put("user_type", "userType");
        fieldMap.put("avatar_url", "avatarUrl");
        fieldMap.put("gender", "gender");
        fieldMap.put("open_id", "openId");
        fieldMap.put("session_key", "sessionKey");
        fieldMap.put("city", "city");
        fieldMap.put("province", "province");
        fieldMap.put("country", "country");
        fieldMap.put("language", "language");
        fieldMap.put("add_time", "addTime");
        fieldMap.put("upd_time", "updTime");
        return fieldMap;
    }
}
