package com.facedamon.controller;

import com.facedamon.beans.JsonData;
import com.facedamon.dto.DeptLevelDto;
import com.facedamon.param.DeptParam;
import com.facedamon.service.SysDeptService;
import com.facedamon.service.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: facedamon
 * @Description:
 * @Date: Credted in 下午9:28 2018/5/18
 * @Modified by:
 */
@Controller
@RequestMapping("/sys/dept")
@Slf4j
public class SysDeptController {

    @Resource
    private SysDeptService sysDeptService;

    @Resource
    private SysTreeService sysTreeService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveDept(DeptParam param){
        sysDeptService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/tree.json")
    @ResponseBody
    public JsonData treeDept(){
        List<DeptLevelDto> deptList = sysTreeService.deptTree();
        return JsonData.success(deptList);
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateDept(DeptParam param){
        sysDeptService.update(param);
        return  JsonData.success();
    }

    @RequestMapping("/page.page")
    public ModelAndView page(){
        return new ModelAndView("dept");
    }

}
