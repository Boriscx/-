package com.cy.pj.sys.controller;

import com.cy.pj.sys.entity.SysDept;
import com.cy.pj.sys.pojo.JsonResult;
import com.cy.pj.sys.service.SysDeptService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dept/")
public class SysDeptController {
    private SysDeptService sysDeptService;
    public SysDeptController(SysDeptService sysDeptService){
        this.sysDeptService = sysDeptService;
    }

    @RequestMapping("doFindObjects")
    public JsonResult<List<SysDept>> doFindObjects(){
        return new JsonResult<>(sysDeptService.findObjects());
    }

    @RequestMapping("doFindZTreeNodes")
    public JsonResult<List<Map<String,Object>>> doFindZTreeNodes(){
        return new JsonResult<>(sysDeptService.doFindZTreeNodes());
    }
}
