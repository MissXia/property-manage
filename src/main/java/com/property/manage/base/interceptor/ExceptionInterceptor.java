package com.property.manage.base.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.property.manage.base.model.constants.SessionContants;
import com.property.manage.base.model.exception.CacheException;
import com.property.manage.base.model.exception.ParameterException;
import com.property.manage.base.model.exception.ServiceException;
import com.property.manage.base.model.exception.SessionException;
import com.property.manage.base.model.model.Response;
import com.property.manage.base.model.utils.CastUtils;
import com.property.manage.base.model.utils.RequestParamUtils;
import com.property.manage.base.session.SessionService;
import com.property.manage.common.pojo.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

public class ExceptionInterceptor implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

    private String defaultEncoding = "UTF-8";

    @Autowired
    protected SessionService sessionService;

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception ex) {

        Map<String, Object> model = Maps.newHashMap();
        model.put("ex", ex);
        String result = null;

        StringBuffer exceptionMsg = new StringBuffer();
        exceptionMsg.append("\n\t");
        try {
            HttpSession session = sessionService.getSession(request);
            UserInfo userInfo = CastUtils.cast(session.getAttribute(SessionContants.SESSION_USER));
            Long userId = null == userInfo ? null : userInfo.getId();
            String userName = null == userInfo ? null : userInfo.getNickName();
            exceptionMsg.append("用户: ");
            if (StringUtils.isNotBlank(userName)) {
                userName = URLDecoder.decode(userName, "UTF-8");
                exceptionMsg.append(userName);
            }
            if (null != userId) {
                exceptionMsg.append("(").append(userId).append(")");
            }
            exceptionMsg.append("】");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = request.getRequestURL().toString();
        Map<String, String[]> parameters = request.getParameterMap();

        exceptionMsg.append("\n\t");
        exceptionMsg.append("请求时间: ");
        exceptionMsg.append(DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        exceptionMsg.append("请求：");
        exceptionMsg.append("\n\t");
        exceptionMsg.append("访问链接：");
        exceptionMsg.append(url);
        exceptionMsg.append("\n\t");
        exceptionMsg.append("访问参数：");
        if (parameters != null && !parameters.isEmpty()) {
            exceptionMsg.append(CastUtils.castJsonString(parameters));
        }
        try {
            exceptionMsg.append("requestBody中的参数: ");
            exceptionMsg.append(RequestParamUtils.getBodyString(request));
        } catch (IOException e) {
            logger.error("获得请求体中的参数异常", e);
        }
        logger.error(exceptionMsg.toString(), ex);
        Response ajaxResponse = handleException(request, ex);
        if (request.getRequestURI().endsWith("rjson")) {
            try {
                responseJson(request, response, ajaxResponse);
            } catch (IOException e) {
                logger.error("响应JSON异常信息的时候报错了.", e);
            }
            return null;
        } else {
            request.setAttribute("response", ajaxResponse);
            result = "view-error";
        }
        return new ModelAndView(result, model);
    }

    private Response handleException(HttpServletRequest request, Exception except) {
        Response response = new Response();
        response.setResult(400);
        response.setMessage("系统处理异常");
        response.setApiName(request.getParameter("apiName"));
        if (except instanceof ParameterException) {
            ParameterException pe = (ParameterException) except;
            response.setResult(pe.getCode());
            response.setMessage(pe.getMessage());
        } else if (except instanceof CacheException) {
            CacheException pe = (CacheException) except;
            response.setMessage(pe.getMessage());
        } else if (except instanceof ServiceException) {
            ServiceException pe = (ServiceException) except;
            response.setResult(pe.getCode());
            response.setMessage(pe.getMessage());
        } else if (except instanceof SessionException) {
            SessionException pe = (SessionException) except;
            response.setResult(pe.getCode());
            response.setMessage(pe.getMessage());
        }
        return response;
    }

    private void responseJson(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws IOException {
        httpServletResponse.setContentType("text/html;charset=" + defaultEncoding);
        PrintWriter pw = null;
        try {
            pw = httpServletResponse.getWriter();
            if (o == null) o = new Object();
            if (o instanceof String) {
                pw.print(o);
            } else {
                pw.print(JSONObject.toJSONString(o));
            }
        } finally {
            if (pw != null)
                pw.close();
        }

    }

    public String getDefaultEncoding() {
        return defaultEncoding;
    }

    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }
}
