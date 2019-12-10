package com.cy.pj.sys.controller;

import com.cy.pj.sys.aspect.annotation.RequestLog;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.pojo.JsonResult;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user/")
public class SysUserController {

    private SysUserService sysUserService;

    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @RequestMapping("doFindPageObjects")
    public JsonResult<PageObject<SysUser>> doFindObjects(String username, Integer pageCurrent) {
        return new JsonResult<>(sysUserService.findObjects(username, pageCurrent, 10));
    }

    @RequestLog("添加用户")
    @RequestMapping("doSaveObject")
    public JsonResult<Integer> doSaveObject(SysUser sysUser) {
        //System.out.println("sysUserController:"+sysUser);
        return new JsonResult<>(sysUserService.saveObject(sysUser));
    }

    @RequestMapping("doFindObjectById")
    public JsonResult<SysUser> doFindObjectById(Integer id) {
        return new JsonResult<>(sysUserService.findObjectById(id));
    }

    @RequestLog("更新用户")
    @PostMapping("doUpdateObject")
    public JsonResult<Integer> doUpdateObject(SysUser sysUser) {
        return new JsonResult<>(sysUserService.updateObject(sysUser));
    }

    @RequestLog("更新用户-启用/禁用")
    @PostMapping("doValidById")
    public JsonResult<Integer> doValidById(Integer id, Integer valid) {
        System.out.println("id:" + id + ",valid:" + valid);
        return new JsonResult<>(sysUserService.updateValidById(id, valid));
    }
}
