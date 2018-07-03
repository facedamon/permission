package com.facedamon.common;

import com.facedamon.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: facedamon
 * @Description:
 * @Date: Credted in 下午9:05 2018/5/18
 * @Modified by:
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI().toString();
        Map paramMap = request.getParameterMap();
        log.info("request start.url:{},params:{}",url,JsonMapper.obj2String(paramMap));
        long start = System.currentTimeMillis();
        request.setAttribute("START_TIME",start);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURI().toString();
        long start = (long) request.getAttribute("START_TIME");
        long end = System.currentTimeMillis();
        RequestHolder.remove();
        log.info("request completed.url:{},cost:{}",url,end - start);
    }
}
