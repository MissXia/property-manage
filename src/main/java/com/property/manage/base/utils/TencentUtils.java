package com.property.manage.base.utils;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.property.manage.base.constants.WebConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 腾讯接口公共类
 */
public class TencentUtils {

    private static Logger logger = LoggerFactory.getLogger(TencentUtils.class);

    /**
     * 执行POST请求
     *
     * @param json
     * @param params
     * @param codeField
     * @param msgField
     */
    public static JSONObject doPost(String url, JSONObject json, Map<String, Object> params, String codeField, String msgField) {
        try {
            logger.info("执行POST请求{}", url);
            // 执行请求
            String result = WebUtils.doPost(HttpClientUtils.makeHtmlParams(url, params), Maps.<String, String>newHashMap(), WebConstants.HTTP_TIMEOUT, WebConstants.HTTP_TIMEOUT);
            //HttpClientUtils.post(HttpClientUtils.makeHtmlParams(url, params));
            logger.info("执行POST请求{},参数{},请求结果为{}", url, JSONObject.toJSONString(params), result);
            // 处理请求结果
            return resultJson(json, result, codeField, msgField);
        } catch (Exception e) {
            logger.info("请求{}执行失败(请求发生异常)", url);
            // 取得信息
            errorJson(json, null, "请求执行失败(请求发生异常)", codeField, msgField);
            // 打印日志
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 执行GET请求
     *
     * @param json
     * @param params
     * @param codeField
     * @param msgField
     */
    public static JSONObject doGet(String url, JSONObject json, Map<String, Object> params, String codeField, String msgField) {
        try {
            logger.info("执行GET请求{}", url);
            // 执行请求
            String result = WebUtils.doGet(HttpClientUtils.makeHtmlParams(url, params), Maps.<String, String>newHashMap());
            logger.info("执行GET请求{},参数{},请求结果为{}", url, JSONObject.toJSONString(params), result);
            // 处理请求结果
            return resultJson(json, result, codeField, msgField);
        } catch (Exception e) {
            logger.info("请求{}执行失败(请求发生异常)", url);
            // 取得信息
            errorJson(json, null, "请求执行失败(请求发生异常)", codeField, msgField);
            // 打印日志
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 处理请求结果
     *
     * @param json
     * @param result
     * @param codeField
     * @param msgField
     */
    public static JSONObject resultJson(JSONObject json, String result, String codeField, String msgField) {
        // 异常处理
        if (StringUtils.isBlank(result)) {
            logger.info("请求执行失败(请求没有返回值)");
            // 错误信息
            errorJson(json, null, "请求执行失败(请求没有返回值)", codeField, msgField);
            // 中断流程
            return null;
        }
        // 解析Json
        JSONObject obj = JSONObject.parseObject(result);
        // 异常判断
        if (null == obj) {
            logger.info("请求执行失败(请求不是JSON格式)");
            // 错误信息
            errorJson(json, null, "请求执行失败(请求不是JSON格式)", codeField, msgField);
            // 中断流程
            return null;
        }
        // 如果不指定错误字段
        if (StringUtils.isBlank(codeField) || StringUtils.isBlank(msgField)) {
            // 直接返回
            return obj;
        }
        // 取得结果状态
        Integer code = obj.getInteger(codeField);
        // 如果不为0
        if (null != code && code != 0) {
            // 错误信息
            String msg = obj.getString(msgField);
            logger.info("请求执行失败({})", msg);
            // 取得信息
            errorJson(json, code, msg, codeField, msgField);
            // 中断流程
            return null;
        }
        // 返回对象
        return obj;
    }

    /**
     * JSON错误信息封装
     *
     * @param json
     * @param msg
     * @param codeField
     * @param msgField
     */
    public static void errorJson(JSONObject json, Integer code, String msg, String codeField, String msgField) {
        // 错误代码
        json.put(codeField, null == code ? -1 : code);
        // 错误信息
        json.put(msgField, msg);
    }

    /**
     * 初始化返回结果
     *
     * @param codeField
     * @param msgField
     * @return
     */
    public static JSONObject initJson(String codeField, String msgField) {
        // 实例化结果
        JSONObject json = new JSONObject();

        json.put(codeField, 0);
        // 错误信息
        json.put(msgField, "调用成功");
        // 返回结果
        return json;
    }

    /**
     * 转换参数集合
     *
     * @param params
     * @return
     */
    public static List<NameValuePair> convertParams(Map<String, Object> params) {
        // 参数列表
        List<NameValuePair> pairs = Lists.newArrayList();
        // 循环Map处理结果
        for (String key : params.keySet()) {
            // 取值
            Object value = params.get(key);
            // 实例化参数
            NameValuePair pair = new BasicNameValuePair(key, JSONObject.toJSONString(value));
            // 添加元素
            pairs.add(pair);
        }
        // 返回列表
        return pairs;
    }
}