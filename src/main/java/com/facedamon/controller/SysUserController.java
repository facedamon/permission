package com.facedamon.controller;

import com.facedamon.beans.JsonData;
import com.facedamon.beans.PageQuery;
import com.facedamon.beans.PageResult;
import com.facedamon.model.SysUser;
import com.facedamon.param.UserParam;
import com.facedamon.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author facedamon
 * @Description:
 * @Date: Credted in 下午7:57 2018/7/1
 * @Modified by:
 */
@RequestMapping("/sys/user")
@Controller
@Slf4j
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData save(UserParam userParam){
        sysUserService.save(userParam);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData update(UserParam userParam){
        sysUserService.update(userParam);
        return JsonData.success();
    }

    @RequestMapping("page.json")
    @ResponseBody
    public JsonData page(@RequestParam("deptId") int deptId, PageQuery page){
        PageResult<SysUser> result = sysUserService.getPageByDeptId(deptId,page);
        return JsonData.success(result);
    }
}
