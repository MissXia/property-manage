package com.property.manage.base.model.utils;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.property.manage.base.model.constants.CommonConstants;
import com.property.manage.base.model.model.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * list 去重
 */
public class ListUtils {

    private static final Object[] DEFAULT_EMPTY_INVOKE_PARAMS = new Object[0];

    private static final Splitter sharp = Splitter.on(CommonConstants.STR_SHARP).trimResults().omitEmptyStrings();

    /**
     * 生成分页对象
     *
     * @param list
     * @param page
     * @param pageSize
     * @param <T>
     * @return
     */
    public static <T> Result<T> makePageResult(List<T> list, Integer page, Integer pageSize) {
        // 当前页码
        page = null == page ? CommonConstants.FIRST_PAGE : page;
        // 分页条数
        pageSize = null == pageSize ? CommonConstants.PAGE_SIZE_20 : pageSize;
        // 总记录数
        int totalCount = null == list ? 0 : list.size();
        // 开始行数
        int fromIndex = (page - 1) * pageSize;
        // 开始行数异常处理,不能小于0
        fromIndex = fromIndex < 0 ? 0 : fromIndex;
        // 开始行数异常处理,不能大于最大记录数
        fromIndex = fromIndex >= totalCount ? totalCount : fromIndex;
        // 结束行数
        int toIndex = page * pageSize;
        // 结束行数异常处理,不能大于最大记录数
        toIndex = toIndex >= totalCount ? totalCount : toIndex;
        // 实例化结果
        Result<T> result = new Result<T>();
        // 设定总记录数
        result.setCount(totalCount);
        // 分割数组
        result.setList(null != list ? list.subList(fromIndex, toIndex) : null);
        // 返回对象
        return result;
    }

    /**
     * 生成分页列表
     *
     * @param list
     * @param page
     * @param pageSize
     * @param <T>
     * @return
     */
    public static <T> List<T> makePageList(List<T> list, Integer page, Integer pageSize) {
        // 当前页码
        page = null == page ? CommonConstants.FIRST_PAGE : page;
        // 分页条数
        pageSize = null == pageSize ? CommonConstants.PAGE_SIZE_20 : pageSize;
        // 总记录数
        int totalCount = null == list ? 0 : list.size();
        // 开始行数
        int fromIndex = (page - 1) * pageSize;
        // 开始行数异常处理,不能小于0
        fromIndex = fromIndex < 0 ? 0 : fromIndex;
        // 开始行数异常处理,不能大于最大记录数
        fromIndex = fromIndex >= totalCount ? totalCount : fromIndex;
        // 结束行数
        int toIndex = page * pageSize;
        // 结束行数异常处理,不能大于最大记录数
        toIndex = toIndex >= totalCount ? totalCount : toIndex;
        // 返回对象
        return null != list ? list.subList(fromIndex, toIndex) : null;
    }

    /**
     * 去空
     *
     * @param list
     * @return
     */
    public static List<String> removeNullEmpty(List<String> list) {
        // 异常处理
        if (null == list || list.isEmpty()) {
            // 中断流程
            return null;
        }
        // 拼接字符串
        String joinStr = org.apache.commons.lang3.StringUtils.join(list, CommonConstants.STR_SHARP);
        // 分割字符串
        return sharp.splitToList(joinStr);
    }

    /**
     * 列表去重
     *
     * @param list
     */
    public static <T> void removeDuplicate(List<T> list) {
        // 异常处理
        if (null == list || list.isEmpty()) {
            // 中断流程
            return;
        }
        // 生成Set,会将list中重复的覆盖掉
        Set<T> h = Sets.newHashSet(list);
        // 清空List
        list.clear();
        // 添加所有元素
        list.addAll(h);
    }

    /**
     * 添加全部元素
     *
     * @param target
     * @param source
     * @param <T>
     */
    public static <T> List<T> addAll(List<T> target, List<T> source) {
        // 异常判断
        if (null == target) {
            // 中断流程
            target = Lists.newArrayList();
        }
        // 异常判断
        if (null == source || source.isEmpty()) {
            // 中断流程
            return target;
        }
        // 添加元素
        target.addAll(source);
        // 中断流程
        return target;
    }

    /**
     * splitAry方法<br>
     * 2014-8-4 上午10:45:36
     *
     * @param array   要分割的数组
     * @param subSize 分割的块大小
     * @return
     */
    public static <T> List<List<T>> splitArray(List<T> array, int subSize) {
        // 分割次数
        int count = array.size() % subSize == 0 ? array.size() / subSize : array.size() / subSize + 1;
        // 新集合
        List<List<T>> subAryList = Lists.newArrayList();
        // 循环处理
        for (int i = 0; i < count; i++) {
            // 起始索引
            int startIndex = i * subSize;
            // 结束索引
            int endIndex = (i + 1) * subSize;
            // 异常处理
            endIndex = endIndex > array.size() ? array.size() : endIndex;
            // 添加分割数组
            subAryList.add(array.subList(startIndex, endIndex));
        }
        return subAryList;
    }

    /**
     * 删除元素
     *
     * @param array
     * @param value
     */
    public static List<String> removeObject(List<String> array, String value) {
        // 异常处理
        if (null == array || array.isEmpty() || StringUtils.isBlank(value)) {
            // 中断流程
            return array;
        }
        // 实例化MAP
        Map<String, String> map = Maps.newHashMap();
        // 循环处理
        for (String val : array) {
            // 排除值
            if (!val.equals(value)) {
                // 添加元素
                map.put(val, val);
            }
        }
        // 转换列表
        return map2List(map);
    }

    /**
     * 将Map转换成List
     *
     * @param <K> Map键值的类型
     * @param <V> Map值的类型
     * @param map 待转换的Map
     */
    public static <K, V> List<V> map2List(Map<K, V> map) {
        // 异常处理
        if (collectionIsEmpty(map)) {
            // 中断流程
            return Lists.newArrayList();
        }
        // 结果列表
        List<V> resultList = Lists.newArrayList();
        // Map的Key集合
        Set<K> keySet = map.keySet();
        // Key集合的迭代器
        Iterator<K> iter = keySet.iterator();
        // 循环处理
        while (iter.hasNext()) {
            // 取得当前KEY
            K key = iter.next();
            // 添加当前值到结果列表
            resultList.add(map.get(key));
        }
        // 返回结果列表
        return resultList;
    }

    /**
     * 将list转换成List
     *
     * @param <K>   Map键值的类型
     * @param <V>   Map值的类型
     * @param list  待转换的List
     * @param field list中的字段
     */
    public static <V, K> List<K> list2List(List<V> list, String field) {
        // 异常处理
        if (collectionIsEmpty(list)) {
            // 中断流程
            return Lists.newArrayList();
        }
        List<K> resultList = Lists.newArrayList();
        Method method = BeanUtils.getPropertyDescriptor(list.get(0).getClass(), field).getReadMethod();
        method.setAccessible(true);
        try {
            for (V value : list) {
                K k = CastUtils.cast(method.invoke(value, DEFAULT_EMPTY_INVOKE_PARAMS));
                if (k != null) {
                    resultList.add(k);
                }
            }
        } catch (Exception e) {

        }
        return resultList;
    }

    /**
     * 将List转成Map
     *
     * @param <K>   Map键值的类型
     * @param <V>   Map值的类型
     * @param list  待转换的List集合
     * @param field 使用哪个field作为Map的key
     */
    public static <K, V> Map<K, V> list2Map(List<V> list, String field) {
        // 异常处理
        if (collectionIsEmpty(list)) {
            // 中断流程
            return Maps.newHashMap();
        }
        Map<K, V> resultMap = Maps.newHashMap();
        Method method = BeanUtils.getPropertyDescriptor(list.get(0).getClass(), field).getReadMethod();
        method.setAccessible(true);
        try {
            for (V value : list) {
                K key = CastUtils.cast(method.invoke(value, DEFAULT_EMPTY_INVOKE_PARAMS));
                resultMap.put(key, value);
            }
        } catch (Exception e) {

        }
        return resultMap;
    }

    /**
     * 将List转成Map
     *
     * @param <K>      Map键值的类型
     * @param <V>      Map值的类型
     * @param list     待转换的List集合
     * @param keyField 使用哪个field作为Map的key
     * @param valField
     */
    public static <K, V, T> Map<K, V> list2Map(List<T> list, String keyField, String valField) {
        // 异常处理
        if (collectionIsEmpty(list)) {
            // 中断流程
            return Maps.newHashMap();
        }
        Map<K, V> resultMap = Maps.newHashMap();
        T item = list.get(0);
        Method keyMethod = BeanUtils.getPropertyDescriptor(item.getClass(), keyField).getReadMethod();
        keyMethod.setAccessible(true);
        Method valMethod = BeanUtils.getPropertyDescriptor(item.getClass(), valField).getReadMethod();
        valMethod.setAccessible(true);
        try {
            for (T value : list) {
                K key = CastUtils.cast(keyMethod.invoke(value, DEFAULT_EMPTY_INVOKE_PARAMS));
                V val = CastUtils.cast(valMethod.invoke(value, DEFAULT_EMPTY_INVOKE_PARAMS));
                resultMap.put(key, val);
            }
        } catch (Exception e) {

        }
        return resultMap;
    }

    /**
     * 将List转成Map
     *
     * @param <V>    Map值的类型
     * @param list   待转换的List集合
     * @param fields 使用哪个field作为Map的key
     */
    public static <V> Map<String, V> list2Map(List<V> list, String spliter, String... fields) {
        // 异常处理
        if (collectionIsEmpty(list)) {
            // 中断流程
            return Maps.newHashMap();
        }
        Map<String, V> resultMap = Maps.newHashMap();
        try {
            List<Method> methods = Lists.newArrayList();
            for (String field : fields) {
                Method method = BeanUtils.getPropertyDescriptor(list.get(0).getClass(), field).getReadMethod();
                method.setAccessible(true);
                methods.add(method);
            }
            for (V value : list) {
                List<String> keys = Lists.newArrayList();
                for (Method method : methods) {
                    String key = CastUtils.cast(method.invoke(value, DEFAULT_EMPTY_INVOKE_PARAMS));
                    keys.add(key);
                }
                resultMap.put(StringUtils.join(keys, spliter), value);
            }
        } catch (Exception e) {

        }
        return resultMap;
    }

    /**
     * 判断集合是空，该集合为List的子类
     */
    public static <C extends List<?>> boolean collectionIsEmpty(C c) {
        return c == null || c.size() == 0;
    }

    /**
     * 判断集合不是空，该集合为List的子类
     */
    public static <C extends List<?>> boolean collectionIsNotEmpty(C c) {
        return !collectionIsEmpty(c);
    }

    /**
     * 判断集合是空，该集合为Map的子类
     */
    public static <C extends Map<?, ?>> boolean collectionIsEmpty(C c) {
        return c == null || c.size() == 0;
    }

    /**
     * 判断集合不是空，该集合为Map的子类
     */
    public static <C extends Map<?, ?>> boolean collectionIsNotEmpty(C c) {
        return !collectionIsEmpty(c);
    }

    /**
     * 判断集合是空，该集合为Set的子类
     */
    public static <C extends Set<?>> boolean collectionIsEmpty(C c) {
        return c == null || c.size() == 0;
    }

    /**
     * 判断集合不是空，该集合为Set的子类
     */
    public static <C extends Set<?>> boolean collectionIsNotEmpty(C c) {
        return !collectionIsEmpty(c);
    }
}
