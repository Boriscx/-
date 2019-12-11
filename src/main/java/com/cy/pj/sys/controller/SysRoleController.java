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
        return new JsonResult<>(sysRoleService.findPageObject(name, pageCurrent, null));
    }

    @PostMapping("doDeleteObject")
    public JsonResult<Integer> doDeleteObject(Integer id) {
        return new JsonResult<>(sysRoleService.deleteObject(id));
    }

    @PostMapping("doSaveObject")
    public JsonResult<Integer> doSaveObject(SysRole sysRole) {
        return new JsonResult<>(sysRoleService.saveObject(sysRole));
    }

    @RequestMapping("doFindObjectById")
    public JsonResult<SysRole> doFindObjectById(Integer id){
        return new JsonResult<>(sysRoleService.getObjectById(id));
    }

    @PostMapping("doUpdateObject")
    public JsonResult<Integer> doUpdateObject(SysRole sysRole){
        return new JsonResult<>(sysRoleService.updateObject(sysRole));
    }

    @GetMapping("doFindRoles")
    public JsonResult<List> doFindRoles(){
        return new JsonResult<>(sysRoleService.findMapObjects());
    }

}
