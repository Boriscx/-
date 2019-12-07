package com.cy.pj.sys.controller;

import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.pojo.JsonResult;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user/")
public class SysUserController {

    private SysUserService sysUserService;

    public SysUserController(SysUserService sysUserService){
        this.sysUserService = sysUserService;
    }

    @RequestMapping("doFindPageObjects")
    public JsonResult<PageObject<SysUser>> doFindObjects(String username, Integer pageCurrent){
        return new JsonResult<>(sysUserService.findObjects(username,pageCurrent,10));
    }

}
