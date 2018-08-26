package com.property.manage.app.service.user;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.property.manage.app.model.po.user.*;
import com.property.manage.base.constants.MiniConstants;
import com.property.manage.base.model.exception.ParameterException;
import com.property.manage.base.model.model.Response;
import com.property.manage.base.model.model.Result;
import com.property.manage.base.model.utils.CastUtils;
import com.property.manage.base.model.utils.CheckUtils;
import com.property.manage.base.model.utils.MD5Utils;
import com.property.manage.base.service.MiniProgramService;
import com.property.manage.base.session.SessionService;
import com.property.manage.common.enums.UserTypes;
import com.property.manage.common.pojo.LesseeCompany;
import com.property.manage.common.pojo.UserInfo;
import com.property.manage.common.pojo.UserInfoView;
import com.property.manage.common.query.UserInfoQuery;
import com.property.manage.common.query.UserInfoViewQuery;
import com.property.manage.common.service.LesseeCompanyService;
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
    private LesseeCompanyService lesseeCompanyService;

    @Resource
    private SessionService sessionService;

    /**
     * 查询用户列表
     *
     * @param params
     * @return
     * @throws ParameterException
     */
    public Result<UserInfoView> userInfoResult(UserInfo userInfo, UserListParams params) throws ParameterException {
        // 用户信息校验
        CheckUtils.ObjectNotNull(params.getUserType(), "用户类型", null);
        // 如果是普通用户
        if (UserTypes.UNKNOW.getKey().equals(userInfo.getUserType()) || UserTypes.NORMAL.getKey().equals(userInfo.getUserType())) {
            // 中断流程
            throw new ParameterException("没有权限进行此操作!");
        }
        // 查询参数
        UserInfoViewQuery query = new UserInfoViewQuery();
        // 用户类型
        query.setUserType(params.getUserType());
        // 昵称
        query.setNickName(params.getNickName());
        // 手机号码
        query.setPhoneNumber(params.getPhoneNumber());
        // 页码
        query.setPage(params.getPage());
        // 显示条数
        query.setPageSize(params.getPageSize());
        // 按创建时间倒序
        query.orderbyAddTime(false);
        // 返回结果
        return userInfoService.getUserInfoViewListWithPage(query);
    }

    /**
     * 按手机号码查找用户
     *
     * @param phoneNumber
     * @return
     */
    public UserInfo findUserInfoByPhoneNumber(Long exceptUserId, String phoneNumber) {
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
        // 根据ID过滤列表
        infos = filterExceptUserId(exceptUserId, infos);
        // 异常处理
        if (CollectionUtils.isEmpty(infos)) {
            // 中断流程
            return null;
        }
        // 返回数据
        return infos.get(0);
    }

    /**
     * 根据ID过滤列表
     *
     * @param exceptUserId
     * @param infos
     * @return
     */
    public List<UserInfo> filterExceptUserId(Long exceptUserId, List<UserInfo> infos) {
        // 如果不存在排除ID
        if (null == exceptUserId || CollectionUtils.isEmpty(infos)) {
            // 返回列表
            return infos;
        }
        // 过滤后的列表
        List<UserInfo> filters = Lists.newArrayList();
        // 循环处理
        for (UserInfo info : infos) {
            // 如果ID不匹配
            if (!exceptUserId.equals(info.getId())) {
                // 添加元素
                filters.add(info);
            }
        }
        // 返回过滤后的列表
        return filters;
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
        UserInfo phoneInfo = findUserInfoByPhoneNumber(params.getUserId(), params.getPhoneNumber());
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
     * 设定用户密码
     *
     * @param userInfo
     * @param params
     * @throws ParameterException
     */
    public void password(UserInfo userInfo, UserPasswordParams params) throws ParameterException {
        logger.info("==设定用户密码==参数{}", CastUtils.castJsonString(params));
        // 取得用户信息
        UserInfo info = userInfoService.getUserInfoByKey(params.getUserId());
        // 异常处理
        if (null == info) {
            // 中断流程
            throw new ParameterException("用户信息不存在!");
        }
        logger.info("==设定用户密码==用户{}", CastUtils.castJsonString(userInfo));
        // 异常处理
        if (userInfo.getUserType() <= UserTypes.OPEARTOR.getKey()) {
            // 异常处理
            throw new ParameterException("不是员工账号的手机号码,不能设定密码");
        }
        // 设定密码
        info.setPassword(MD5Utils.md5(params.getPassword()));
        logger.info("==设定用户密码==密码{}", info.getPassword());
        // 更新时间
        info.setUpdTime(new Date());
        // 更新数据
        userInfoService.updateUserInfoByKey(info);
        logger.info("==设定用户密码==完成");
    }

    /**
     * 设定企业ID
     *
     * @param userInfo
     * @param params
     * @throws ParameterException
     */
    public void companyId(UserInfo userInfo, UserCompanyParams params) throws ParameterException {
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
        // 取得企业信息
        LesseeCompany company = lesseeCompanyService.getLesseeCompanyByKey(params.getCompanyId());
        // 如果存在
        if (null == company) {
            // 中断流程
            throw new ParameterException("相关企业不存在!");
        }
        // 设定企业ID
        info.setCompanyId(company.getId());
        // 更新时间
        info.setUpdTime(new Date());
        // 更新数据
        userInfoService.updateUserInfoByKey(info);
    }

    /**
     * 编辑用户信息
     *
     * @param userInfo
     * @param params
     * @throws ParameterException
     */
    public void editUser(UserInfo userInfo, UserEditParams params) throws ParameterException {
        // 如果权限小于超级管理员
        if (userInfo.getUserType() < UserTypes.SUPER_ADMIN.getKey()) {
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
        // 按手机号码查找用户
        UserInfo phoneInfo = findUserInfoByPhoneNumber(params.getUserId(), params.getPhoneNumber());
        // 如果存在
        if (null != phoneInfo) {
            // 中断流程
            throw new ParameterException("该手机号码已经有人使用!");
        }
        // 取得企业信息
        LesseeCompany company = lesseeCompanyService.getLesseeCompanyByKey(params.getCompanyId());
        // 如果存在
        if (null == company) {
            // 中断流程
            throw new ParameterException("相关企业不存在!");
        }
        // 设定手机号码
        info.setPhoneNumber(params.getPhoneNumber());
        // 设定企业ID
        info.setCompanyId(company.getId());
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
        if (userInfo.getUserType() <= params.getUserType()) {
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
        info.setUserType(params.getUserType());
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
        // 当前时间
        Date now = new Date();
        // 如果DB中没有数据
        if (null == userInfo) {
            // 参数校验
            CheckUtils.StringNotBlank(info.getPhoneNumber(), "手机号码", null);
            // 根据手机号码取得用户信息
            UserInfo phoneNumber = findUserInfoByPhoneNumber(null, info.getPhoneNumber());
            // 异常判断
            if (null != phoneNumber) {
                // 中断流程
                throw new ParameterException("该手机号码已经被使用!");
            }
            // 生成用户信息数据,默认都是未知类型,待审核
            userInfo = UserInfoUtils.generateUserInfo(null, info.getNickName(), info.getPhoneNumber(), UserTypes.UNKNOW.getKey(), info.getAvatarUrl(), info.getGender(), info.getOpenId(), info.getSessionKey(), info.getCity(), info.getProvince(), info.getCountry(), info.getLanguage());
            // 新增数据
            userInfoService.addUserInfo(userInfo);
            // 返回用户数据
            return userInfo;
        }
        // 认证失败的情况下
        if (UserTypes.FAILD.getKey().equals(userInfo.getUserType())) {
            // 参数校验
            CheckUtils.StringNotBlank(info.getPhoneNumber(), "手机号码", null);
            // 根据手机号码取得用户信息
            UserInfo phoneNumber = findUserInfoByPhoneNumber(userInfo.getId(), info.getPhoneNumber());
            // 异常判断
            if (null != phoneNumber) {
                // 中断流程
                throw new ParameterException("该手机号码已经被使用!");
            }
            // 手机号码
            userInfo.setPhoneNumber(info.getPhoneNumber());
            // 用户类型=未知
            userInfo.setUserType(UserTypes.UNKNOW.getKey());
            // 修改时间
            userInfo.setUpdTime(now);
            // 更新用户
            userInfoService.updateUserInfoByKey(userInfo);
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
        userInfo.setUpdTime(now);
        // 更新用户
        userInfoService.updateUserInfoByKey(userInfo);
        // 返回用户数据
        return userInfo;
    }

    /**
     * PC端登录
     *
     * @param params
     * @return
     * @throws ParameterException
     */
    public UserInfo pcLogin(UserPcLoginParams params) throws ParameterException {
        logger.info("==PC端登录==参数{}", CastUtils.castJsonString(params));
        // 参数校验
        CheckUtils.StringNotBlank(params.getPhoneNumber(), "手机号码", null);
        // 参数校验
        CheckUtils.StringNotBlank(params.getPassword(), "密码", null);
        // 取得DB中的UserInfo
        UserInfo userInfo = findUserInfoByPhoneNumber(null, params.getPhoneNumber());
        // 如果DB中没有数据
        if (null == userInfo) {
            // 异常处理
            throw new ParameterException("该手机号还未绑定账号");
        }
        logger.info("==PC端登录==用户{}", CastUtils.castJsonString(userInfo));
        // 异常处理
        if (userInfo.getUserType() <= UserTypes.OPEARTOR.getKey()) {
            // 异常处理
            throw new ParameterException("不是员工账号的手机号码,不能登录");
        }
        // 异常处理
        if (StringUtils.isBlank(userInfo.getPassword())) {
            // 异常处理
            throw new ParameterException("请先去小程序设定登录密码");
        }
        // 取得MD5值
        String md5 = MD5Utils.md5(params.getPassword());
        logger.info("==PC端登录==校验密码{}", md5);
        // 如果密码不匹配
        if (!userInfo.getPassword().equals(md5)) {
            // 异常处理
            throw new ParameterException("密码不正确");
        }
        // 返回用户信息
        return userInfo;
    }
}