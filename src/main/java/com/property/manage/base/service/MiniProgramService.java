package com.property.manage.base.service;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.property.manage.base.utils.TencentUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


/**
 * 小程序API服务类
 *
 * @author guanhuijun
 *         2016年01月22日19:20
 */
public class MiniProgramService {

    private static Logger logger = LoggerFactory.getLogger(MiniProgramService.class);

    /**
     * AppID(小程序ID)
     */
    private final static String appId = "wx110d933c5300dfad";

    /**
     * AppSecret(小程序密钥)
     */
    private final static String appSecret = "3cb108d85e1e8a38dd1106fbf027b318";

    /**
     * 错误代码
     */
    private final static String ERROR_CODE_FIELD = "errcode";

    /**
     * 错误信息
     */
    private final static String ERROR_MSG_FIELD = "errmsg";

    /**
     * 登录凭证校验
     */
    private final static String JS_CODE_2_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";

    public static void main(String[] a) {
        JSONObject res = jscode2sessionApi("001TPd4q1xk5Fp0r0b5q1B3j4q1TPd4A");
        System.out.print(res);
    }

    /**
     * 登录凭证校验
     *
     * @return
     */
    public static JSONObject jscode2sessionApi(String code) {
        logger.info("==登录凭证校验==开始处理[{}]");
        // 返回结果
        JSONObject json = TencentUtils.initJson(ERROR_CODE_FIELD, ERROR_MSG_FIELD);
        // 公共参数构造
        Map<String, Object> params = Maps.newHashMap();
        // 获取标签成员参数
        String msg = tagApiParams(params, code);
        // 如果有错误信息
        if (StringUtils.isNotBlank(msg)) {
            logger.info("==登录凭证校验==发生异常[{}]", msg);
            // 错误信息
            TencentUtils.errorJson(json, null, msg, ERROR_CODE_FIELD, ERROR_MSG_FIELD);
            // 中断流程
            return json;
        }
        logger.info("==登录凭证校验==开始请求[params:{}]", JSONObject.toJSONString(params));
        // 执行GET请求
        JSONObject result = TencentUtils.doGet(JS_CODE_2_SESSION_URL, json, params, ERROR_CODE_FIELD, ERROR_MSG_FIELD);
        // 异常处理
        if (null != result) {
            // 返回结果
            return result;
        }
        // 返回结果
        return json;
    }

    /**
     * 获取标签成员参数
     *
     * @param params
     * @return
     */
    private static String tagApiParams(Map<String, Object> params, String code) {
        // 异常处理
        if (StringUtils.isBlank(code)) {
            // 中断流程
            return "参数code不能为空";
        }
        // 设定参数
        params.put("js_code", code);
        // 小程序ID
        params.put("appid", appId);
        // 小程序秘钥
        params.put("secret", appSecret);
        // 填写为 authorization_code
        params.put("grant_type", "authorization_code");
        // 返回参数
        return "";
    }
}