package com.cy.pj.sys.controller;

import com.cy.pj.sys.aspect.annotation.RequestLog;
import com.cy.pj.sys.entity.SysDept;
import com.cy.pj.sys.pojo.JsonResult;
import com.cy.pj.sys.service.SysDeptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("doFindObjects")
    public JsonResult<List> doFindObjects(){
        return new JsonResult<>(sysDeptService.findObjects());
    }

    @GetMapping("doFindZTreeNodes")
    public JsonResult<List<Map<String,Object>>> doFindZTreeNodes(){
        return new JsonResult<>(sysDeptService.doFindZTreeNodes());
    }

    @PostMapping("doSaveObject")
    public JsonResult<Integer> doSaveObject(SysDept sysDept){
        sysDeptService.saveObject(sysDept);
        return new JsonResult<>("保存成功");
    }

    @PostMapping("doUpdateObject")
    public JsonResult<Integer> doUpdateObject(SysDept sysDept){
        return new JsonResult<>(sysDeptService.updateObject(sysDept));
    }

    @PostMapping("doDeleteObject")
    public JsonResult<Integer> doDeleteObject(Integer id){
        return new JsonResult<>(sysDeptService.deleteObject(id));
    }
}
