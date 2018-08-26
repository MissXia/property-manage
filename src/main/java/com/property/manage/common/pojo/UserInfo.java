package com.property.manage.common.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.property.manage.base.model.pojo.BasePojo;

import java.util.Date;

/**
 * @author 管辉俊
 * @date 2018-05-22
 */
@JsonIgnoreProperties({"sessionKey"})
public class UserInfo extends BasePojo {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * SessionId
     */
    private String sessionId;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 企业ID
     */
    private Long companyId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型：-1、认证失败 0、未知 1、普通用户 7、普通员工 8、管理员 9、超级管理员
     */
    private Integer userType;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private Integer gender;

    /**
     * 用户唯一标识
     */
    private String openId;

    /**
     * 会话密钥
     */
    private String sessionKey;

    /**
     * 用户所在城市
     */
    private String city;

    /**
     * 用户所在省份
     */
    private String province;

    /**
     * 用户所在国家
     */
    private String country;

    /**
     * 用户的语言，简体中文为zh_CN
     */
    private String language;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 修改时间
     */
    private Date updTime;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return id 自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id 自增主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * @return nickName 用户昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName 用户昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return phoneNumber 手机号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber 手机号码
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return userType 用户类型：0、未知 1、普通用户 7、普通员工 8、管理员 9、超级管理员
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * @param userType 用户类型：0、未知 1、普通用户 7、普通员工 8、管理员 9、超级管理员
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * @return avatarUrl 用户头像
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * @param avatarUrl 用户头像
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * @return gender 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * @param gender 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * @return openId 用户唯一标识
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId 用户唯一标识
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * @return sessionKey 会话密钥
     */
    public String getSessionKey() {
        return sessionKey;
    }

    /**
     * @param sessionKey 会话密钥
     */
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    /**
     * @return city 用户所在城市
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city 用户所在城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return province 用户所在省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province 用户所在省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return country 用户所在国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country 用户所在国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return language 用户的语言，简体中文为zh_CN
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language 用户的语言，简体中文为zh_CN
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return addTime 创建时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime 创建时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return updTime 修改时间
     */
    public Date getUpdTime() {
        return updTime;
    }

    /**
     * @param updTime 修改时间
     */
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

}