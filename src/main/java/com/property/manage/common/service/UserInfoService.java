package com.property.manage.common.service;


import com.property.manage.base.model.model.Result;
import com.property.manage.common.pojo.UserInfo;
import com.property.manage.common.pojo.UserInfoView;
import com.property.manage.common.query.UserInfoQuery;
import com.property.manage.common.query.UserInfoViewQuery;

import java.util.List;

/**
 * @author 管辉俊
 * @since 2018-05-22
 */
public interface UserInfoService {

    /**
     * 基本插入
     *
     * @return
     */
    Long addUserInfo(UserInfo record);

    /**
     * 根据主键查询
     */
    UserInfo getUserInfoByKey(Long id);

    /**
     * 根据openId查询
     *
     * @param openId
     * @return
     */
    UserInfo getUserInfoByOpenId(String openId);

    /**
     * 从缓存中查询
     *
     * @param openId
     * @return
     */
    UserInfo getUserInfoByCache(String openId);

    /**
     * 根据主键删除
     *
     * @return
     */
    Integer deleteByKey(Long id);

    /**
     * 根据主键更新
     *
     * @return
     */
    Integer updateUserInfoByKey(UserInfo record);

    /**
     * 根据条件查询分页查询
     *
     * @param query 查询条件
     * @return
     */
    Result<UserInfo> getUserInfoListWithPage(UserInfoQuery query);

    /**
     * 根据条件查询
     *
     * @param query 查询条件
     * @return
     */
    List<UserInfo> getUserInfoList(UserInfoQuery query);

    /**
     * 根据条件查询分页查询
     *
     * @param query 查询条件
     * @return
     */
    Result<UserInfoView> getUserInfoViewListWithPage(UserInfoViewQuery query);

    /**
     * 根据条件查询
     *
     * @param query 查询条件
     * @return
     */
    List<UserInfoView> getUserInfoViewList(UserInfoViewQuery query);
}
