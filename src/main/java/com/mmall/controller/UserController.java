package com.mmall.controller;

import com.mmall.model.SysUser;
import com.mmall.service.SysUserService;
import com.mmall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: facedamon
 * @Description:
 * @Date: Credted in 下午12:27 2018/7/2
 * @Modified by:
 */
@Controller
@RequestMapping
public class UserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/login.page")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        SysUser sysUser = sysUserService.findByKeyword(username);
        String errorMsg = "";
        /**
         * 远程链接
         */
        String ret = request.getParameter("ret");

        if (StringUtils.isBlank(username)){
            errorMsg = "用户名不能为空";
        }else if (StringUtils.isBlank(password)){
            errorMsg = "密码不能为空";
        }else if (null == sysUser){
            errorMsg = "用户名错误，查询不到指定用户";
        }else if (!StringUtils.equals(sysUser.getPassword(),MD5Util.encrypt(password))){
            errorMsg = "用户名或密码错误";
        }else if (sysUser.getStatus() != 1){
            errorMsg = "用户已被冻结，请联系管理员";
        }else{
            /**
             * loadSuccess
             */
            request.getSession().setAttribute("user",sysUser);
            if (StringUtils.isNotBlank(ret)){
                response.sendRedirect(ret);
            }else {
                //TODO
                response.sendRedirect("/admin/index.page");
            }
        }
        request.setAttribute("error",errorMsg);
        request.setAttribute("username",username);
        if (StringUtils.isNotBlank(ret)){
            request.setAttribute("ret",ret);
        }
        String path = "signin.jsp";
        request.getRequestDispatcher(path).forward(request,response);
    }
}
