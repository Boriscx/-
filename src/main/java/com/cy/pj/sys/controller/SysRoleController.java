package com.cy.pj.sys.controller;

import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.pojo.JsonResult;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysRoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role/")
public class SysRoleController {

    private SysRoleService sysRoleService;

    public SysRoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @RequestMapping("doFindPageObjects")
    public JsonResult<PageObject> doFindPageObjects(String name, Integer pageCurrent) {
        return new JsonResult<>(sysRoleService.findPageObjects(name, pageCurrent));
    }

    @PostMapping("doDeleteObject")
    public JsonResult<Integer> doDeleteObject(Integer id) {
        sysRoleService.deleteObject(id);
        return new JsonResult<>("delete ok");
    }

    @PostMapping("doSaveObject")
    public JsonResult<Integer> doSaveObject(SysRole sysRole) {
        sysRoleService.saveObject(sysRole);
        return new JsonResult<>("保存成功");
    }

    @PostMapping("doUpdateObject")
    public JsonResult<Integer> doUpdateObject(SysRole sysRole){
        sysRoleService.updateObject(sysRole);
        return new JsonResult<>("修改成功");
    }

    @RequestMapping("doFindObjectById")
    public JsonResult<SysRole> doFindObjectById(Integer id){
        return new JsonResult<>(sysRoleService.getObjectById(id));
    }

    @GetMapping("doFindRoles")
    public JsonResult<List> doFindRoles(){
        return new JsonResult<>(sysRoleService.findMapObjects());
    }

}
