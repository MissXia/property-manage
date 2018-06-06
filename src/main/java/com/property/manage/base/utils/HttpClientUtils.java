package com.property.manage.base.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http请求公共类
 * <p/>
 * Created by guanhuijun on 2016/5/23.
 */
public class HttpClientUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * HTTP客户端
     */
    public static HttpClient httpclient = HttpClients.createDefault();

    static {
        // 实例化管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // 设定最大值
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(50);
        // 配置
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(5000).build();
        // 设定配置
        httpclient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(config).build();
    }

    public static CloseableHttpClient getHttpClient(){
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(50);
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(5000).build();
        return HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(config).build();
    }
    /**
     * 生成参数连接
     *
     * @param url
     * @param params
     * @return
     */
    public static String makeHtmlParams(String url, Map<String, Object> params) {
        // 异常处理
        if (null == params || params.isEmpty()) {
            // 中断流程
            return url;
        }
        // 参数列表
        List<String> paramList = Lists.newArrayList();
        // 循环Map处理结果
        for (String key : params.keySet()) {
            // 取值
            Object value = params.get(key);
            try {
                // 拼接参数
                paramList.add(key + "=" + java.net.URLEncoder.encode(value.toString(), "utf-8"));
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        // 返回拼接参数
        return url + "?" + StringUtils.join(paramList, "&");
    }

    /**
     * POST请求
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        // GET请求(带参数)
        return get(url, null);
    }

    /**
     * GET请求(带参数)
     *
     * @param url
     * @return
     */
    public static String get(String url, Map<String, Object> params) {
        try {
            logger.info("==GET请求(带参数)==url:{}", url);
            // 实例化GET请求对象
            HttpGet get = new HttpGet(makeHtmlParams(url, params));
            // 执行请求
            HttpResponse response = httpclient.execute(get);
            // 返回对象
            return handleResponse(response);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            return null;
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
            return null;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * POST请求
     *
     * @param url
     * @return
     */
    public static String post(String url) {
        // POST请求(带参数)
        return post(url, new ArrayList<NameValuePair>());
    }

    /**
     * POST请求(带参数)
     *
     * @param url
     * @param form
     * @return
     */
    public static String post(String url, List<NameValuePair> form) {
        try {
            logger.info("==POST请求(带参数)==url:{}", url);
            // 实例化POST请求
            HttpPost post = new HttpPost(url);
            // 设定参数
            post.setEntity(new UrlEncodedFormEntity(form, Consts.UTF_8));
            // 执行请求
            HttpResponse response = httpclient.execute(post);
            // 返回请求
            return handleResponse(response);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            return null;
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage());
            return null;
        } catch (IOException e) {
            logger.error(e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 处理请求回执
     *
     * @param response
     * @return
     * @throws Exception
     */
    private static String handleResponse(HttpResponse response) throws Exception {
        // 取得回执状态
        int status = response.getStatusLine().getStatusCode();
        // 异常处理
        if (status >= 200 && status < 300) {
            // 取得返回结果
            HttpEntity entity = response.getEntity();
            // 返回结果
            return entity != null ? EntityUtils.toString(entity) : null;
        } else {
            throw new ClientProtocolException("Unexpected response status: " + status);
        }
    }

    public static void main(String[] args) {
        List<NameValuePair> list = Lists.newArrayList();
        list.add(new BasicNameValuePair("name", "ybt"));
        String response = HttpClientUtils.post("http://localhost:8080/info", list);
        System.out.println(response);
    }
}
