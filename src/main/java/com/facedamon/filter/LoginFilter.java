package com.facedamon.filter;

import com.facedamon.common.RequestHolder;
import com.facedamon.constant.Session;
import com.facedamon.model.SysUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: facedamon
 * @Description:
 * @Date: Credted in 上午12:20 2018/7/4
 * @Modified by:
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        SysUser sysUser = (SysUser) request.getSession().getAttribute(Session.USER.getValue());

        if (null == sysUser){
            String path = "/signin.jsp";
            response.sendRedirect(path);
            return;
        }

        RequestHolder.addUserLocal(sysUser);
        RequestHolder.addRequestLocal(request);

        filterChain.doFilter(servletRequest,servletResponse);
        return;
    }

    @Override
    public void destroy() {

    }
}
