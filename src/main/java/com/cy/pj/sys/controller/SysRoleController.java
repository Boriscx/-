package com.cy.pj.sys.controller;

import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.pojo.JsonResult;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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

    @RequestMapping("doDeleteObject")
    public JsonResult<Integer> doDeleteObject(Integer id) {
        return new JsonResult<>(sysRoleService.deleteObject(id));
    }

    @RequestMapping("doSaveObject")
    public JsonResult<Integer> doSaveObject(SysRole sysRole) {
        System.out.println(sysRole);
        return new JsonResult<>(sysRoleService.saveObject(sysRole));
    }

}
