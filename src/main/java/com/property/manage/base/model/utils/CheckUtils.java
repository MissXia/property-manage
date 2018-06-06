package com.property.manage.base.model.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.property.manage.base.model.exception.ParameterException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class CheckUtils {

    private static Logger logger = LoggerFactory.getLogger(CheckUtils.class);

    /**
     * 正则表达式：验证传真号
     */
    public static final String REGEX_FAX = "^(\\d{3,4}(-)?)?\\d{7,8}$";

    /**
     * 正则表达式：验证电话号
     */
    public static final String REGEX_TELE = "(((400|800)(-)?((\\d{3})(-)?(\\d{4})|(\\d{4})(-)?(\\d{3})))|(^\\d{11}$)|^((\\d{7,8})|(\\d{3,4})(-)?(\\d{7,8})|(\\d{3,4})(-)?(\\d{7,8})-(\\d{1,4})|^(\\d{7,8})-(\\d{1,4}))$)";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^(\\w)+([-\\.]\\w+)*@(\\w)+(([-\\.]\\w{2,3}){1,3})$";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "^([a-zA-z]+://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$";

    /**
     * 正则表达式：验证正整数
     */
    public static final String REGEX_INT = "^[0-9]*[1-9][0-9]*$";

    /**
     * 正则表达式：验证整数
     */
    public static final String REGEX_INTEGER = "^-?\\d+$";

    /**
     * 正则表达式：验证正浮点数
     */
    public static final String REGEX_POSITIVE_INTEGER = "^\\d+(\\.\\d+)?$";

    /**
     * 正则表达式：验证浮点数
     */
    public static final String REGEX_FLOAT = "^(-?\\d+)(\\.\\d+)?$";

    /**
     * 正则表达式：验证时间
     */
    public static final String REGEX_TIME = "^(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";

    /**
     * Integer参数有效性校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void IntegerInArray(Integer value, Integer[] array, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (!Lists.newArrayList(array).contains(value)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")值无效,请检查!"));
        }
    }

    /**
     * String参数有效性校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void StringInArray(String value, String[] array, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (!Lists.newArrayList(array).contains(value)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")值无效,请检查!"));
        }
    }

    /**
     * Integer参数值区间校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void IntegerFromTo(Integer value, Integer from, Integer to, String fieldName, String msg) throws ParameterException {
        // 最大最小值都存在
        if (null != from && null != to && (value < from || value > to)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不在[" + from + " - " + to + "]范围内!"));
        }
        // 只存在最小值
        if (null != from && null == to && (value < from)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不能小于" + from + "!"));
        }
        // 只存在最大值
        if (null == from && null != to && value > to) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不能大于" + to + "!"));
        }
    }

    /**
     * Long参数值区间校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void LongFromTo(Long value, Long from, Long to, String fieldName, String msg) throws ParameterException {
        // 最大最小值都存在
        if (null != from && null != to && (value < from || value > to)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不在[" + from + " - " + to + "]范围内!"));
        }
        // 只存在最小值
        if (null != from && null == to && (value < from)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不能小于" + from + "!"));
        }
        // 只存在最大值
        if (null == from && null != to && value > to) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不能大于" + to + "!"));
        }
    }

    /**
     * Double参数值区间校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void FloatFromTo(Float value, Float from, Float to, String fieldName, String msg) throws ParameterException {
        // 最大最小值都存在
        if (null != from && null != to && (value < from || value > to)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不在[" + from + " - " + to + "]范围内!"));
        }
        // 只存在最小值
        if (null != from && null == to && (value < from)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不能小于" + from + "!"));
        }
        // 只存在最大值
        if (null == from && null != to && value > to) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不能大于" + to + "!"));
        }
    }

    /**
     * Double参数值区间校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void DoubleFromTo(Double value, Double from, Double to, String fieldName, String msg) throws ParameterException {
        // 最大最小值都存在
        if (null != from && null != to && (value < from || value > to)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不在[" + from + " - " + to + "]范围内!"));
        }
        // 只存在最小值
        if (null != from && null == to && (value < from)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不能小于" + from + "!"));
        }
        // 只存在最大值
        if (null == from && null != to && value > to) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不能大于" + to + "!"));
        }
    }

    /**
     * 字符串参数非空校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void StringNotBlank(String value, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (!StringUtils.isNotBlank(value)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")必须传入,请检查!"));
        }
    }

    /**
     * 字符串参数非空校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void StringNotStartWith(String value, String startWith, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (StringUtils.isBlank(value) || !value.startsWith(startWith)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")必须以(" + startWith + ")开头,请检查!"));
        }
    }

    /**
     * 邮箱格式校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void StringIsEmail(String value, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (StringUtils.isNotBlank(value) && !Pattern.matches(REGEX_EMAIL, value)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不是正确的邮箱格式!"));
        }
    }

    /**
     * 手机号码格式校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void StringIsMobile(String value, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (StringUtils.isNotBlank(value) && !Pattern.matches(REGEX_MOBILE, value)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不是正确的手机号码格式!"));
        }
    }

    /**
     * 电话号码格式校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void StringIsTelephone(String value, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (StringUtils.isNotBlank(value) && !Pattern.matches(REGEX_TELE, value)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不是正确的电话号码格式!"));
        }
    }

    /**
     * 传真号码格式校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void StringIsFax(String value, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (StringUtils.isNotBlank(value) && !Pattern.matches(REGEX_FAX, value)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不是正确的传真号码格式!"));
        }
    }

    /**
     * 网址格式校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void StringIsUrl(String value, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (StringUtils.isNotBlank(value) && !Pattern.matches(REGEX_URL, value)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不是正确的网址格式!"));
        }
    }

    /**
     * 整数格式校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void StringIsInteger(String value, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (StringUtils.isNotBlank(value) && !Pattern.matches(REGEX_INTEGER, value)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不是正确的整数格式!"));
        }
    }

    /**
     * 浮点数格式校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void StringIsFloat(String value, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (StringUtils.isNotBlank(value) && !Pattern.matches(REGEX_FLOAT, value)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不是正确的浮点数格式!"));
        }
    }

    /**
     * 金额格式校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void StringIsAmount(String value, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (StringUtils.isNotBlank(value) && !Pattern.matches(REGEX_POSITIVE_INTEGER, value)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不是正确的金额格式!"));
        }
    }

    /**
     * 百分比格式校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void StringIsPercent(String value, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (StringUtils.isNotBlank(value) && !Pattern.matches(REGEX_FLOAT, value)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不是正确的百分比格式!"));
        }
    }

    /**
     * 时间格式校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void StringIsTime(String value, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (StringUtils.isNotBlank(value) && !Pattern.matches(REGEX_TIME, value)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不是正确的时间格式!"));
        }
    }

    /**
     * 日期格式校验
     *
     * @param value
     * @param format
     * @param fieldName
     * @param msg
     */
    public static void StringIsDate(Object value, String format, String fieldName, String msg) throws ParameterException {
        // 如果是时间类型
        if (null != value && value instanceof Date) {
            // 中断流程
            return;
        }
        // 入参异常判断
        if (null != value && StringUtils.isNotBlank(value.toString()) && StringUtils.isNotBlank(format)) {
            // 转换时间
            Date date = null;
            try {
                // 转换时间
                date = DateUtils.parseDate(value.toString(), format);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            // 转换类型失败
            if (null == date) {
                // 错误信息
                throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不是正确的日期(时间)格式!"));
            }
        }
    }

    /**
     * 字符串是JSON对象校验
     *
     * @param value
     * @param fieldName
     * @param msg
     * @return
     */
    public static void StringIsJsonObject(String value, Class clazz, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (StringUtils.isBlank(value) || value.equals("{}")) {
            // 中断流程
            return;
        }
        try {
            // 转换格式
            JSONObject.parseObject(value, clazz);
        } catch (Exception e) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不是正确JSON格式!"));
        }
    }

    /**
     * 字符串是JSON数组校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void StringIsJsonArray(String value, Class clazz, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (StringUtils.isBlank(value) || value.equals("[]")) {
            // 中断流程
            return;
        }
        try {
            // 转换格式
            JSONArray.parseArray(value, clazz);
        } catch (Exception e) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")不是正确JSON格式!"));
        }
    }

    /**
     * 日期参数非空校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void ObjectNotNull(Object value, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (null == value) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")必须传入,请检查!"));
        }
    }

    /**
     * 字符串长度校验
     *
     * @param value
     * @param minLen
     * @param maxLen
     * @param fieldName
     * @param msg
     */
    public static void StringLength(String value, Integer minLen, Integer maxLen, String fieldName, String msg) throws ParameterException {
        // 最大最小值都存在
        if (null != minLen && null != maxLen && (value.length() < minLen || value.length() > maxLen)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")的长度不在[" + minLen + " - " + maxLen + "]个字范围内!"));
        }
        // 只存在最小值
        if (null != minLen && null == maxLen && (value.length() < minLen)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")的长度不能小于" + minLen + "个字!"));
        }
        // 只存在最大值
        if (null == minLen && null != maxLen && value.length() > maxLen) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")的长度不能大于" + maxLen + "个字!"));
        }
    }

    /**
     * 字符串参数非空校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void ListNotEmpty(List<?> value, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (CollectionUtils.isEmpty(value)) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")的至少要有一个!"));
        }
    }

    /**
     * 字符串参数非空校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void MapNotEmpty(Map value, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (null == value || value.isEmpty()) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")的集合不能为空!"));
        }
    }

    /**
     * 字符串参数非空校验
     *
     * @param value
     * @param fieldName
     * @param msg
     */
    public static void ArrayNotEmpty(Object[] value, String fieldName, String msg) throws ParameterException {
        // 入参异常判断
        if (value.length == 0) {
            // 错误信息
            throw new ParameterException(StringUtils.isNotBlank(msg) ? msg : ("(" + fieldName + ")至少选择一个!"));
        }
    }
}
