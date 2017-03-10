package com.dolton.component;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class InOutInterceptor extends HandlerInterceptorAdapter {

    static Logger _log = LogManager.getLogger(InOutInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("text/plain;charset=UTF-8");
        return true;
    }

    @Override
    public void postHandle(final HttpServletRequest request,
                           HttpServletResponse response, final Object handler,
                           final ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }
}