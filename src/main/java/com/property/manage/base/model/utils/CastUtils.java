package com.property.manage.base.model.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.property.manage.base.model.constants.CommonConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CastUtils {

    private static final transient Logger logger = LoggerFactory.getLogger(CastUtils.class);

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        T cast = null;
        try {
            cast = (T) obj;
        } catch (Exception e) {
            logger.info("Cast Object failed!");
        }
        return cast;
    }

    /**
     * 转换JSON字符串
     *
     * @param obj
     * @return
     */
    public static String castJsonString(Object obj) {
        // 异常处理
        if (null == obj) {
            // 中断流程
            return CommonConstants.STR_BLANK;
        }
        // 转换字符串
        return JSON.toJSONString(obj);
    }

    /**
     * 判断是否是数字
     *
     * @param value
     * @return
     */
    public static boolean isNumeric(String value) {
        // 判断非空
        if (StringUtils.isBlank(value)) {
            // 不是数字
            return false;
        }
        // 转换类型
        try {
            double res = Double.parseDouble(value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Description: 转换为Int类型<br>
     *
     * @param str
     * @return int
     */
    public static Integer castToInt(String str) {
        // 最终结果
        Integer res = null;
        // 判断非空
        if (StringUtils.isBlank(str)) {
            // 中断流程
            return null;
        }
        // 转换类型
        try {
            res = Integer.parseInt(str);
        } catch (Exception e) {
            logger.info("Cast Integer failed!");
        }
        return res;
    }

    /**
     * Description: 转换为Long类型<br>
     *
     * @param str
     * @return long
     */
    public static Long castToLong(String str) {
        // 最终结果
        Long res = null;
        // 判断非空
        if (StringUtils.isBlank(str)) {
            // 中断流程
            return null;
        }
        // 转换类型
        try {
            res = Long.parseLong(str);
        } catch (Exception e) {
            logger.info("Cast Long failed!");
        }
        return res;
    }

    /**
     * Description: 转换为Float类型<br>
     *
     * @param str
     * @return long
     */
    public static Float castToFloat(String str) {
        // 最终结果
        Float res = null;
        // 判断非空
        if (StringUtils.isBlank(str)) {
            // 中断流程
            return null;
        }
        // 转换类型
        try {
            res = Float.parseFloat(str);
        } catch (Exception e) {
            logger.info("Cast Double failed!");
        }
        return res;
    }

    /**
     * Description: 转换为Double类型<br>
     *
     * @param str
     * @return long
     */
    public static Double castToDouble(String str) {
        // 最终结果
        Double res = null;
        // 判断非空
        if (StringUtils.isBlank(str)) {
            // 中断流程
            return null;
        }
        // 转换类型
        try {
            res = Double.parseDouble(str);
        } catch (Exception e) {
            logger.info("Cast Double failed!");
        }
        return res;
    }

    /**
     * 转换对象到MAP
     *
     * @param bean
     * @param map
     * @return
     */
    public static <T> Map<String, Object> castBeanToMapWithOutJsonField(T bean, Map<String, Object> map) {
        // 异常处理
        if (null == map || null == bean) {
            // 实例化对象
            map = Maps.newHashMap();
        }
        // 转换类型
        try {
            // 取得Bean属性集合
            BeanMap beanMap = BeanMap.create(bean);
            // 异常判断
            if (null != beanMap) {
                // 循环处理
                for (Object key : beanMap.keySet()) {
                    // 取得Bean的驼峰属性名称
                    String fieldName = key.toString();
                    // 添加元素
                    map.put(fieldName, beanMap.get(key));
                }
            }
        } catch (Exception e) {
            logger.info("Cast Bean to map failed!");
        }
        // 返回MAP
        return map;
    }

    /**
     * 转换MAP(带排除字段)
     *
     * @param bean
     * @param map
     * @param exceptFields
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> castBeanToMap(T bean, Map<String, Object> map, String... exceptFields) {
        // 异常处理
        if (null == map || null == bean) {
            // 实例化对象
            map = Maps.newHashMap();
        }
        // 排除字段列表
        List<String> excepts = Lists.newArrayList(exceptFields);
        // 转换类型
        try {
            // 取得Bean属性集合
            BeanMap beanMap = BeanMap.create(bean);
            // 异常判断
            if (null != beanMap) {
                // 循环处理
                for (Object key : beanMap.keySet()) {
                    // 取得Bean的驼峰属性名称
                    String fieldName = key.toString();
                    // 如果不包含
                    if (!excepts.contains(fieldName)) {
                        // 添加元素
                        map.put(fieldName, beanMap.get(key));
                    }
                }
            }
        } catch (Exception e) {
            logger.info("Cast Bean to map failed!");
        }
        // 返回MAP
        return map;
    }

    /**
     * 转换对象到MAP
     *
     * @param bean
     * @param map
     * @return
     */
    public static <T> Map<String, Object> castBeanToMap(T bean, Map<String, Object> map) {
        // 异常处理
        if (null == map || null == bean) {
            // 实例化对象
            map = Maps.newHashMap();
        }
        // 转换类型
        try {
            // 取得JSONField映射名称集合
            Map<String, String> jsonNames = findJSONFieldNameKeys(bean);
            // 取得Bean属性集合
            BeanMap beanMap = BeanMap.create(bean);
            // 异常判断
            if (null != beanMap) {
                // 循环处理
                for (Object key : beanMap.keySet()) {
                    // 取得Bean的驼峰属性名称
                    String fieldName = key.toString();
                    // 取得JSONField的name值
                    String jsonName = jsonNames.get(fieldName);
                    // 如果取得
                    if (StringUtils.isNotBlank(jsonName)) {
                        // 添加元素
                        map.put(jsonName, beanMap.get(key));
                    }
                }
            }
        } catch (Exception e) {
            logger.info("Cast Bean to map failed!");
        }
        // 返回MAP
        return map;
    }

    /**
     * 取得JSONField映射名称集合
     *
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> Map<String, String> findJSONFieldNameKeys(T bean) {
        // 名称集合
        Map<String, String> fieldMap = Maps.newHashMap();
        // 取得字段列表
        List<Field> fields = findAllFields(bean);
        // 异常处理
        if (null == fields || fields.isEmpty()) {
            // 中断流程
            return fieldMap;
        }
        // 循环处理
        for (Field field : fields) {
            // 取得JSON字段名称
            String fieldName = findJSONFieldName(field);
            // 异常判断
            if (StringUtils.isNotBlank(fieldName)) {
                // 添加元素
                fieldMap.put(field.getName(), fieldName);
            }
        }
        // 返回集合
        return fieldMap;
    }

    /**
     * 取得JSON字段名称
     *
     * @param field
     * @return
     */
    private static String findJSONFieldName(Field field) {
        // 判断是否有注解
        if (!field.isAnnotationPresent(JSONField.class)) {
            // 中断流程
            return null;
        }
        // 取得有效注解
        JSONField jsonField = field.getAnnotation(JSONField.class);
        // 异常处理
        if (null == jsonField) {
            // 中断流程
            return null;
        }
        // 取得JSON名称
        return jsonField.name();
    }

    /**
     * 取得对象的所有字段列表
     *
     * @param bean
     * @return
     */
    public static <T> List<Field> findAllFields(T bean) {
        // 所有字段列表
        List<Field> fields = Lists.newArrayList();
        // 取得对应类型
        Class tmpClass = bean.getClass();
        // 循环处理
        while (tmpClass != null && !tmpClass.getName().toLowerCase().equals("java.lang.object")) {
            // 当父类为null的时候说明到达了最上层的父类(Object类).
            fields.addAll(Arrays.asList(tmpClass.getDeclaredFields()));
            // 得到父类,然后赋给自己
            tmpClass = tmpClass.getSuperclass();
        }
        // 返回字段列表
        return fields;
    }

    /**
     * map转换为bean
     *
     * @param map
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T castMapToBean(Map<String, Object> map, T bean) {
        // 取得beanMap
        BeanMap beanMap = BeanMap.create(bean);
        // 设定所有元素
        beanMap.putAll(map);
        // 返回bean
        return bean;
    }

    /**
     * 生成参数集合
     *
     * @param params
     * @param names
     * @param values
     * @return
     */
    public static Map<String, Object> makeParams(Map<String, Object> params, String[] names, Object... values) {
        // 如果为空
        if (null == params) {
            // 实例化map
            params = Maps.newHashMap();
        }
        // 参数长度
        int size = names.length;
        // 循环处理
        for (int i = 0; i < size; i++) {
            // 参数名称
            String name = names[i];
            // 参数值
            Object value = values.length > i ? values[i] : null;
            // 设定值
            params.put(name, value);
        }
        // 返回参数列表
        return params;
    }

    /**
     * 转换json
     *
     * @param json
     * @return
     */
    public static Map<String, Object> makeJsonToObjectMap(String json) {
        try {
            // 异常处理
            if (StringUtils.isBlank(json)) {
                // 中断流程
                return null;
            }
            // 取得错误数据
            return JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        // 返回null
        return null;
    }

    /**
     * 转换JSON
     *
     * @param json
     * @param classz
     * @param <T>
     * @return
     */
    public static <T> T parseJson(String json, Class<T> classz) {
        // 异常处理
        if (StringUtils.isBlank(json)) {
            // 中断流程
            return null;
        }
        try {
            // 取得错误数据
            return JSON.parseObject(json, classz);
        } catch (Exception e) {
            logger.error("json={},error={}",json,e.getMessage());
        }
        // 返回null
        return null;
    }

    /**
     * 转换JSON
     *
     * @param json
     * @param classz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseJsonArray(String json, Class<T> classz) {
        // 异常处理
        if (StringUtils.isBlank(json)) {
            // 中断流程
            return null;
        }
        try {
            // 取得错误数据
            return JSON.parseArray(json, classz);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        // 返回null
        return null;
    }

    /**
     * 转换数据
     *
     * @param obj
     * @return
     */
    public static String[] castToStringArray(Object obj) {
        // 异常处理
        if (null == obj) {
            // 中断流程
            return null;
        }
        // 如果不是数组
        if (!obj.getClass().isArray()) {
            // 中断流程
            return null;
        }
        // 取得数组长度
        int length = Array.getLength(obj);
        // 实例化数组
        String[] os = new String[length];
        // 循环处理
        for (int i = 0; i < os.length; i++) {
            // 取得数组项
            Object object = Array.get(obj, i);
            // 设定数组项
            os[i] = null == object ? null : object.toString();
        }
        // 返回数组
        return os;
    }

    /**
     * 转换列表
     *
     * @param obj
     * @return
     */
    public static List<String> castToStringList(Object obj) {
        // 异常处理
        if (null == obj) {
            // 中断流程
            return null;
        }
        // 如果不是列表类型
        if (!(obj instanceof List)) {
            // 中断流程
            return null;
        }
        // 转换类型
        return cast(obj);
    }

    /**
     * url encode
     *
     * @param url
     * @param charset
     * @return
     */
    public static String urlEncode(String url, String charset) {
        try {
            // url encode
            url = URLEncoder.encode(url, StringUtils.isNotBlank(charset) ? charset : CommonConstants.CHARSET_UTF_8);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return url;
    }

    /**
     * url decode
     *
     * @param url
     * @param charset
     * @return
     */
    public static String urlDecode(String url, String charset) {
        try {
            // url decode
            url = URLDecoder.decode(url, StringUtils.isNotBlank(charset) ? charset : CommonConstants.CHARSET_UTF_8);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return url;
    }
}