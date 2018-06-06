package com.property.manage.base.controller;

import com.property.manage.base.session.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 基础控制器
 */
@Controller
public abstract class BaseController {

    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected SessionService sessionService;

    @ModelAttribute
    protected void setHttpServlet(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
        // session管理
        sessionService.setHttpServlet(session);
    }
}