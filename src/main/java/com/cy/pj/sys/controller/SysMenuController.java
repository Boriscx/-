package com.cy.pj.sys.controller;

import com.cy.pj.sys.aspect.annotation.RequestLog;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.pojo.JsonResult;
import com.cy.pj.sys.pojo.Node;
import com.cy.pj.sys.service.SysMenuService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu/")
public class SysMenuController {

    private SysMenuService sysMenuService;

    public SysMenuController(SysMenuService sysMenuService){
        this.sysMenuService = sysMenuService;
    }

    @RequestLog("菜单管理-删除菜单")
    @RequestMapping("doDeleteObject")
    public JsonResult doDeleteObject(Integer id){
        sysMenuService.deleteObject(id);
        return new JsonResult("delete ok");
    }

    //@RequestLog("菜单管理-查看全部菜单")
    @RequestMapping("doFindObjects")
    public JsonResult findObjects(){
        return new JsonResult<>(sysMenuService.findObjects());
    }

    //@RequestLog("菜单管理-查找全部菜单")
    @RequestMapping("doFindZtreeMenuNodes")
    public JsonResult findZtreeMenuNodes(){
        return new JsonResult<>(sysMenuService.findZtreeMenuNodes());
    }

    @RequestLog("菜单管理-新增菜单")
    @RequestMapping("doSaveObject")
    public JsonResult doSaveObject(@Validated SysMenu sysMenu){
        return new JsonResult<>(sysMenuService.saveObject(sysMenu));
    }

    @RequestLog("菜单管理-修改菜单")
    @RequestMapping("doUpdateObject")
    public JsonResult doUpdateObject(SysMenu sysMenu){

        return new JsonResult<>(sysMenuService.updateObject(sysMenu));
    }
}
