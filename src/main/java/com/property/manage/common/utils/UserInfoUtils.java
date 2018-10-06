package com.property.manage.common.utils;

import com.property.manage.common.pojo.UserInfo;

import java.util.Date;

/**
 * Created by guanhuijun on 2018/6/2.
 */
public class UserInfoUtils {

    /**
     * 生成用户信息对象
     *
     * @param info
     * @param nickName
     * @param phoneNumber
     * @param userType
     * @param avatarUrl
     * @param gender
     * @param openId
     * @param sessionKey
     * @param city
     * @param province
     * @param country
     * @param language
     * @return
     */
    public static UserInfo generateUserInfo(UserInfo info, String nickName, String phoneNumber, Integer userType, String avatarUrl, Integer gender, String openId, String sessionKey, String city, String province, String country, String language) {
        // 如果是新增的情况
        if (null == info) {
            // 实例化对象
            info = new UserInfo();
            // 创建时间
            info.setAddTime(new Date());
        }
        // 用户昵称
        info.setNickName(nickName);
        // 手机号码
        info.setPhoneNumber(phoneNumber);
        // 用户类型：0、未知 1、普通用户 6、普通员工 7、维护员工 8、管理员 9、超级管理员
        info.setUserType(userType);
        // 用户头像
        info.setAvatarUrl(avatarUrl);
        // 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
        info.setGender(gender);
        // 用户唯一标识
        info.setOpenId(openId);
        // 会话密钥
        info.setSessionKey(sessionKey);
        // 用户所在城市
        info.setCity(city);
        // 用户所在省份
        info.setProvince(province);
        // 用户所在国家
        info.setCountry(country);
        // 用户的语言，简体中文为zh_CN
        info.setLanguage(language);
        // 修改时间
        info.setUpdTime(new Date());
        // 返回对象
        return info;
    }
}
