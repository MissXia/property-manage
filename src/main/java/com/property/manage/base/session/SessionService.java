package com.property.manage.base.session;

import com.property.manage.base.model.constants.SessionContants;
import com.property.manage.base.model.exception.SessionException;
import com.property.manage.base.model.utils.CastUtils;
import com.property.manage.common.pojo.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 管理session的Service
 *
 * @author huzhengnan
 *         2015年03月20日19:25
 */
@Service
public class SessionService {

    private ThreadLocal<HttpSession> sessionLocal = new ThreadLocal<HttpSession>();

    @ModelAttribute
    public void setHttpServlet(HttpSession session) {
        this.sessionLocal.set(session);
    }

    public HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

    public HttpSession getSession() throws SessionException {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {
            // 中断流程
            throw new SessionException("获取Session失败!");
        }
        return session;
    }

    public HttpSession getSession(HttpServletRequest request) throws SessionException {
        HttpSession session = null;
        try {
            session = request.getSession();
        } catch (Exception e) {
            // 中断流程
            throw new SessionException("获取Session失败!");
        }
        return session;
    }

    /**
     * 向session中设置用户
     *
     * @param userInfo
     */
    public void setUserInfo(UserInfo userInfo) {
        set(SessionContants.SESSION_USER, userInfo);
    }

    /**
     * 向session中删除用户
     */
    public void deleteUserInfo() {
        delete(SessionContants.SESSION_USER);
    }

    /**
     * 从session中获取用户
     */
    public UserInfo getUserInfo() throws SessionException {
        // 取得用户信息
        UserInfo userInfo = CastUtils.cast(get(SessionContants.SESSION_USER));
        // 异常处理
        if (null == userInfo) {
            // 中断流程
            throw new SessionException("用户Session失效,请重新登录!");
        }
        // 返回用户信息
        return userInfo;
    }

    /**
     * 设置session的通用接口，不对外提供
     *
     * @param key
     * @param value
     * @return
     */
    private Object set(String key, Object value) {
        getHttpSession().setAttribute(key, value);
        Object o = getHttpSession().getAttribute(key);
        if (o.equals(value)) {
            return o;
        }
        return null;
    }

    /**
     * 获取session的通用接口，不对外提供
     *
     * @param key
     * @return
     */
    private Object get(String key) {
        return getHttpSession().getAttribute(key);
    }

    /**
     * 跟新session的通用接口，不对外提供
     *
     * @param key
     * @param value
     * @return
     */
    private Object update(String key, Object value) {
        getHttpSession().setAttribute(key, value);
        Object o = getHttpSession().getAttribute(key);
        if (o.equals(value)) {
            return o;
        }
        return null;
    }

    /**
     * 删除session的通用接口，不对外提供
     *
     * @param key
     */
    private void delete(String key) {
        if (null != getHttpSession().getAttribute(key)) {
            getHttpSession().removeAttribute(key);
        }
    }

    /**
     * 获取session对象，不对外提供
     *
     * @return
     */
    private HttpSession getHttpSession() {
        return this.sessionLocal.get();
    }
}
