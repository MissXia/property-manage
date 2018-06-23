package com.property.manage.app.service.user;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.property.manage.app.model.po.user.*;
import com.property.manage.base.constants.MiniConstants;
import com.property.manage.base.model.exception.ParameterException;
import com.property.manage.base.model.model.Response;
import com.property.manage.base.model.model.Result;
import com.property.manage.base.model.utils.CheckUtils;
import com.property.manage.base.service.MiniProgramService;
import com.property.manage.base.session.SessionService;
import com.property.manage.common.enums.UserTypes;
import com.property.manage.common.pojo.UserInfo;
import com.property.manage.common.query.UserInfoQuery;
import com.property.manage.common.service.UserInfoService;
import com.property.manage.common.utils.UserInfoUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 管辉俊
 * @since 2018-05-22
 */
@Service
public class UserInfoProcessService {

    private static Logger logger = LoggerFactory.getLogger(UserInfoProcessService.class);

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private SessionService sessionService;

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
        // 单元编号
        query.setUnitNumber(params.getUnitNumber());
        // 手机号码
        query.setPhoneNumber(params.getPhoneNumber());
        // 页码
        query.setPage(params.getPage());
        // 显示条数
        query.setPageSize(params.getPageSize());
        // 按创建时间倒序
        query.orderbyAddTime(false);
        // 返回结果
        return userInfoService.getUserInfoListWithPage(query);
    }

    /**
     * 按手机号码查找用户
     *
     * @param phoneNumber
     * @return
     */
    public UserInfo findUserInfoByPhoneNumber(String phoneNumber) {
        // 查询参数
        UserInfoQuery query = new UserInfoQuery();
        // 手机号码
        query.setPhoneNumber(phoneNumber);
        // 页码
        query.setPage(1);
        // 显示条数
        query.setPageSize(1);
        // 查询列表
        List<UserInfo> infos = userInfoService.getUserInfoList(query);
        // 异常处理
        if (CollectionUtils.isEmpty(infos)) {
            // 中断流程
            return null;
        }
        // 返回数据
        return infos.get(0);
    }

    /**
     * 设定用户手机号码
     *
     * @param userInfo
     * @param params
     * @throws ParameterException
     */
    public void phoneNumber(UserInfo userInfo, UserPhoneParams params) throws ParameterException {
        // 取得用户信息
        UserInfo info = userInfoService.getUserInfoByKey(userInfo.getId());
        // 异常处理
        if (null == info) {
            // 中断流程
            throw new ParameterException("用户信息不存在!");
        }
        // 按手机号码查找用户
        UserInfo phoneInfo = findUserInfoByPhoneNumber(params.getPhoneNumber());
        // 如果存在
        if (null != phoneInfo) {
            // 中断流程
            throw new ParameterException("该手机号码已经有人使用!");
        }
        // 设定手机号码
        info.setPhoneNumber(params.getPhoneNumber());
        // 更新时间
        info.setUpdTime(new Date());
        // 更新数据
        userInfoService.updateUserInfoByKey(info);
    }

    /**
     * 设定单元编号
     *
     * @param userInfo
     * @param params
     * @throws ParameterException
     */
    public void unitNumber(UserInfo userInfo, UserUnitParams params) throws ParameterException {
        // 如果权限小于普通员工
        if (userInfo.getUserType() < UserTypes.OPEARTOR.getKey()) {
            // 中断流程
            throw new ParameterException("您无权进行此操作!");
        }
        // 取得用户信息
        UserInfo info = userInfoService.getUserInfoByKey(params.getUserId());
        // 异常处理
        if (null == info) {
            // 中断流程
            throw new ParameterException("用户信息不存在!");
        }
        // 设定为用户类型
        info.setUnitNumber(params.getUnitNumber());
        // 更新时间
        info.setUpdTime(new Date());
        // 更新数据
        userInfoService.updateUserInfoByKey(info);
    }

    /**
     * 审核用户类型
     *
     * @param userInfo
     * @param params
     * @throws ParameterException
     */
    public void verifyUserType(UserInfo userInfo, UserVerifyTypeParams params) throws ParameterException {
        // 如果权限小于普通员工
        if (userInfo.getUserType() <= params.getUserType().getKey()) {
            // 中断流程
            throw new ParameterException("您无权进行此操作!");
        }
        // 取得用户信息
        UserInfo info = userInfoService.getUserInfoByKey(params.getUserId());
        // 异常处理
        if (null == info) {
            // 中断流程
            throw new ParameterException("用户信息不存在!");
        }
        // 设定为用户类型
        info.setUserType(params.getUserType().getKey());
        // 更新时间
        info.setUpdTime(new Date());
        // 更新数据
        userInfoService.updateUserInfoByKey(info);
    }

    /**
     * 登录检查
     *
     * @param params
     * @return
     * @throws ParameterException
     */
    public Response loginCheck(HttpServletRequest request, UserLoginParams params) throws ParameterException {
        // 登录code校验
        CheckUtils.StringNotBlank(params.getCode(), "登录code", null);
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
        // 返回数据实例化
        Map<String, Object> data = Maps.newHashMap();
        // 取得DB中的UserInfo
        UserInfo userInfo = userInfoService.getUserInfoByOpenId(openId);
        // 如果有注册过
        if (null != userInfo) {
            // 取得SessionId
            userInfo.setSessionId(request.getSession().getId());
            // 如果DB中有数据说明注册过
            data.put("userInfo", userInfo);
            // 清空用户Session
            sessionService.deleteUserInfo();
            // 设定用户Session
            sessionService.setUserInfo(userInfo);
        }
        // 如果没有注册过则返回
        if (null == userInfo) {
            // openId
            data.put("openId", openId);
            // sessionKey
            data.put("sessionKey", sessionKey);
        }
        // 返回数据
        return Response.success(data);
    }

    /**
     * 登录
     *
     * @param params
     * @return
     * @throws ParameterException
     */
    public UserInfo login(UserLoginParams params) throws ParameterException {
        // 取得参数中的info
        UserInfo info = params.getUserInfo();
        // 用户信息校验
        CheckUtils.ObjectNotNull(info, "用户信息", null);
        // 参数校验
        CheckUtils.StringNotBlank(info.getOpenId(), "用户openId", null);
        // 参数校验
        CheckUtils.StringNotBlank(info.getSessionKey(), "用户sessionKey", null);
        // 取得DB中的UserInfo
        UserInfo userInfo = userInfoService.getUserInfoByOpenId(info.getOpenId());
        // 如果DB中没有数据
        if (null == userInfo) {
            // 生成用户信息数据,默认都是未知类型,待审核
            userInfo = UserInfoUtils.generateUserInfo(null, info.getNickName(), null, null, UserTypes.UNKNOW.getKey(), info.getAvatarUrl(), info.getGender(), info.getOpenId(), info.getSessionKey(), info.getCity(), info.getProvince(), info.getCountry(), info.getLanguage());
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
        userInfo.setOpenId(info.getOpenId());
        // 会话密钥
        userInfo.setSessionKey(info.getSessionKey());
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