package com.mmall.common;

import com.mmall.model.SysUser;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: facedamon
 * @Description:
 * @Date: Credted in 上午12:14 2018/7/4
 * @Modified by:
 */
public class RequestHolder {
    private static ThreadLocal<SysUser> userThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();

    public static void addUserLocal(SysUser sysUser){
        userThreadLocal.set(sysUser);
    }

    public static void addRequestLocal(HttpServletRequest request){
        requestThreadLocal.set(request);
    }

    public static SysUser getUserLocal(){
        return userThreadLocal.get();
    }

    public static HttpServletRequest getRequestLocal(){
        return requestThreadLocal.get();
    }

    public static void remove(){
        userThreadLocal.remove();
        requestThreadLocal.remove();
    }
}
