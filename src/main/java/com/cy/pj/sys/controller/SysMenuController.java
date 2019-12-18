package com.cy.pj.sys.controller;

import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.pojo.JsonResult;
import com.cy.pj.sys.service.SysMenuService;
import com.cy.pj.sys.util.ShiroUntil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/menu/")
public class SysMenuController {

    private SysMenuService sysMenuService;

    public SysMenuController(SysMenuService sysMenuService){
        this.sysMenuService = sysMenuService;
    }

    @RequestMapping("doDeleteObject")
    public JsonResult doDeleteObject(Integer id){
        sysMenuService.deleteObject(id);
        return new JsonResult("delete ok");
    }

    @RequestMapping("doFindObjects")
    public JsonResult findObjects(){
        return new JsonResult<>(sysMenuService.findMapObjects());
    }

    @RequestMapping("doFindZtreeMenuNodes")
    public JsonResult findZTreeMenuNodes(){
        return new JsonResult<>(sysMenuService.findZTreeMap());
    }

    @GetMapping("doFindMenusByUserId")
    public JsonResult<Set> findMenusByUserId(){
        Integer userId = ShiroUntil.getUser().getId();
        return new JsonResult<>(sysMenuService.findMenusByUserId(userId));
    }

    @PostMapping("doSaveObject")
    public JsonResult doSaveObject(@Validated SysMenu sysMenu){
        sysMenuService.saveObject(sysMenu);
        return new JsonResult<>("save ok");
    }

    @PostMapping("doUpdateObject")
    public JsonResult doUpdateObject(SysMenu sysMenu){
        sysMenuService.updateObject(sysMenu);
        return new JsonResult<>("修改保存完毕");
    }
}
