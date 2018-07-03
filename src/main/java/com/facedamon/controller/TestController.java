package com.facedamon.controller;

import com.facedamon.common.ApplicationContextHelper;
import com.facedamon.beans.JsonData;
import com.facedamon.dao.SysAclModuleMapper;
import com.facedamon.model.SysAclModule;
import com.facedamon.param.TestVo;
import com.facedamon.util.BeanValidator;
import com.facedamon.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Authoe facedamon
 * @Description:
 * @Date: Credted in 下午11:59 2018/5/14
 * @Modified by:
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/hello.json")
    @ResponseBody
    public JsonData hello(){
        log.info("hello");
        throw new RuntimeException("test exception");
        //return JsonData.success("hello permission");
    }

    @RequestMapping("/validate.json")
    @ResponseBody
    public JsonData validate(TestVo testVo){
        SysAclModuleMapper moduleMapper = ApplicationContextHelper.popBean(SysAclModuleMapper.class);
        SysAclModule module = moduleMapper.selectByPrimaryKey(1);
        log.info(JsonMapper.obj2String(module));

        BeanValidator.check(testVo);
        return JsonData.success("test validate");
    }
}
