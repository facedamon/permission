package com.mmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: facedamon
 * @Description:
 * @Date: Credted in 下午12:50 2018/7/2
 * @Modified by:
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("index.page")
    public ModelAndView index(){
        return new ModelAndView("admin");
    }
}
