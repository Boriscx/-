package com.cy.pj.sys.controller;

import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.pojo.JsonResult;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysUserService;
import com.cy.pj.sys.util.ShiroUntil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    @PostMapping("doSaveObject")
    public JsonResult<Integer> doSaveObject(@Validated SysUser sysUser) {
        return new JsonResult<>(sysUserService.saveObject(sysUser));
    }

    @RequestMapping("doFindObjectById")
    public JsonResult<SysUser> doFindObjectById(Integer id) {
        return new JsonResult<>(sysUserService.findObjectById(id));
    }

    @PostMapping("doUpdateObject")
    public JsonResult<Integer> doUpdateObject(SysUser sysUser) {
        return new JsonResult<>(sysUserService.updateObject(sysUser));
    }

    @PostMapping("doValidById")
    public JsonResult<Integer> doValidById(Integer id, Integer valid) {
        System.out.println("id:" + id + ",valid:" + valid);
        return new JsonResult<>(sysUserService.updateValidById(id, valid));
    }

    @RequestMapping("doLogin")
    public JsonResult<String> doLogin(String username, String password, Boolean isRememberMe) {
        // 获取subject对象
        Subject subject = SecurityUtils.getSubject();
        // 封装用户信息
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(isRememberMe);
        // 提交信息并处理
        subject.login(token);
//        subject.isRemembered();
        return new JsonResult<>("login ok");
    }

    @PostMapping("doLogout")
    public JsonResult<String> doLogout() {
        SecurityUtils.getSubject().logout();
        return new JsonResult<>();
    }

    @PostMapping("doUpdatePassword")
    public JsonResult<String> doUpdatePassword(String pwd, String newPwd, String cfgPwd) {
        sysUserService.updatePassword(pwd, newPwd, cfgPwd);
        return new JsonResult<>();
    }

    @GetMapping("isExists")
    public JsonResult<String> isExists(String columnName, String columnValue) {
        sysUserService.isExists(columnName, columnValue);
        return new JsonResult<>();
    }

    @GetMapping("getUsername")
    public JsonResult<Map> getUserName() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",ShiroUntil.getUsername());
        return new JsonResult<>(map);
    }
}
