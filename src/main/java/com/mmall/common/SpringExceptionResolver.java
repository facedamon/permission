package com.mmall.common;

import com.mmall.exception.ParamException;
import com.mmall.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: facedamon
 * @Description:
 * @Date: Credted in 下午10:16 2018/5/14
 * @Modified by:
 */
@Slf4j
public class SpringExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        String url = httpServletRequest.getRequestURI().toString();
        ModelAndView mv = null;
        String defaultMsg = "System error";

        // .json,.page
        // 这里我们要求项目中所有请求json数据，都使用.json结尾
        if(url.endsWith(".json")){
            if (e instanceof PermissionException || e instanceof ParamException){
                JsonData result = JsonData.failer(e.getMessage());
                mv = new ModelAndView("jsonView",result.toMap());
            }else{
                log.error("unkonwn json exception url:{},exception:{}",url,e);
                JsonData result = JsonData.failer(defaultMsg);
                mv = new ModelAndView("jsonView",result.toMap());
            }
            /**
             *  这里我们要求项目中所有请求page数据，都使用.page结尾
             */
        }else if(url.endsWith(".page")){
            log.error("unkonwn page exception url:{},exception:{}",url,e);
            JsonData result = JsonData.failer(defaultMsg);
            mv = new ModelAndView("exception",result.toMap());
        }else{
            log.error("unkonwn exception url:{},exception:{}",url,e);
            JsonData result = JsonData.failer(defaultMsg);
            mv = new ModelAndView("jsonView",result.toMap());
        }
        return mv;
    }
}
