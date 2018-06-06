package com.property.manage.app.service;

import com.alibaba.fastjson.JSONObject;
import com.property.manage.app.model.po.UserListParams;
import com.property.manage.app.model.po.UserLoginParams;
import com.property.manage.app.model.po.UserOperateParams;
import com.property.manage.base.constants.MiniConstants;
import com.property.manage.base.model.exception.ParameterException;
import com.property.manage.base.model.model.Result;
import com.property.manage.base.model.utils.CheckUtils;
import com.property.manage.base.service.MiniProgramService;
import com.property.manage.common.enums.UserTypes;
import com.property.manage.common.pojo.UserInfo;
import com.property.manage.common.query.UserInfoQuery;
import com.property.manage.common.service.UserInfoService;
import com.property.manage.common.utils.UserInfoUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author 管辉俊
 * @since 2018-05-22
 */
@Service
public class UserLoginService {

    private static Logger logger = LoggerFactory.getLogger(UserLoginService.class);

    @Resource
    private UserInfoService userInfoService;

    /**
     * 查询用户列表
     *
     * @param params
     * @return
     * @throws ParameterException
     */
    public Result<UserInfo> userInfoResult(UserInfo userInfo, UserListParams params) throws ParameterException {
        // 用户信息校验
        CheckUtils.ObjectNotNull(params.getUserType(), "用户类型", null);
        // 如果是普通用户
        if (UserTypes.UNKNOW.getKey().equals(userInfo.getUserType()) || UserTypes.NORMAL.getKey().equals(userInfo.getUserType())) {
            // 中断流程
            throw new ParameterException("没有权限进行此操作!");
        }
        // 查询参数
        UserInfoQuery query = new UserInfoQuery();
        // 用户类型
        query.setUserType(params.getUserType());
        // 昵称
        query.setNickName(params.getNickName());
        // 页码
        query.setPage(params.getPage());
        // 显示条数
        query.setPageSize(params.getPageSize());
        // 返回结果
        return userInfoService.getUserInfoListWithPage(query);
    }

    public void verifyNormal(UserOperateParams params) {
        //
        UserInfo info = userInfoService.getUserInfoByKey(params.getUserId());
    }

    /**
     * 登录
     *
     * @param params
     * @return
     * @throws ParameterException
     */
    public UserInfo login(UserLoginParams params) throws ParameterException {
        // 登录code校验
        CheckUtils.StringNotBlank(params.getCode(), "登录code", null);
        // 用户信息校验
        CheckUtils.ObjectNotNull(params.getUserInfo(), "用户信息", null);
        // 登录凭证校验
        JSONObject object = MiniProgramService.jscode2sessionApi(params.getCode());
        // 异常处理
        if (null == object) {
            // 中断流程
            throw new ParameterException("登录凭证校验失败!");
        }
        // 取得openId
        String openId = object.getString(MiniConstants.JSON_RESULT_FIELD_OPENID);
        // 取得sessionKey
        String sessionKey = object.getString(MiniConstants.JSON_RESULT_FIELD_SESSION_KEY);
        // 异常处理
        if (StringUtils.isBlank(openId) || StringUtils.isBlank(sessionKey)) {
            logger.info(JSONObject.toJSONString(object));
            // 中断流程
            throw new ParameterException("登录凭证校验失败!");
        }
        // 取得参数中的info
        UserInfo info = params.getUserInfo();
        // 取得DB中的UserInfo
        UserInfo userInfo = userInfoService.getUserInfoByOpenId(openId);
        // 如果DB中没有数据
        if (null == userInfo) {
            // 生成用户信息数据,默认都是未知类型,待审核
            userInfo = UserInfoUtils.generateUserInfo(null, info.getNickName(), null, UserTypes.UNKNOW.getKey(), info.getAvatarUrl(), info.getGender(), openId, sessionKey, info.getCity(), info.getProvince(), info.getCountry(), info.getLanguage());
            // 新增数据
            userInfoService.addUserInfo(userInfo);
            // 返回用户数据
            return userInfo;
        }
        // 用户昵称
        userInfo.setNickName(info.getNickName());
        // 用户头像
        userInfo.setAvatarUrl(info.getAvatarUrl());
        // 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
        userInfo.setGender(info.getGender());
        // 用户唯一标识
        userInfo.setOpenId(openId);
        // 会话密钥
        userInfo.setSessionKey(sessionKey);
        // 用户所在城市
        userInfo.setCity(info.getCity());
        // 用户所在省份
        userInfo.setProvince(info.getProvince());
        // 用户所在国家
        userInfo.setCountry(info.getCountry());
        // 用户的语言，简体中文为zh_CN
        userInfo.setLanguage(info.getLanguage());
        // 修改时间
        userInfo.setUpdTime(new Date());
        // 更新用户
        userInfoService.updateUserInfoByKey(userInfo);
        // 返回用户数据
        return userInfo;
    }
}