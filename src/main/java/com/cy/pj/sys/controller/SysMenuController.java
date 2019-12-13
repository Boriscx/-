package com.cy.pj.sys.controller;

import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.pojo.JsonResult;
import com.cy.pj.sys.service.SysMenuService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        return new JsonResult<>(sysMenuService.findObjects());
    }

    @RequestMapping("doFindZtreeMenuNodes")
    public JsonResult findZtreeMenuNodes(){
        return new JsonResult<>(sysMenuService.findZTreeMap());
    }

    @PostMapping("doSaveObject")
    public JsonResult doSaveObject(@Validated SysMenu sysMenu){
        return new JsonResult<>(sysMenuService.saveObject(sysMenu));
    }

    @PostMapping("doUpdateObject")
    public JsonResult doUpdateObject(SysMenu sysMenu){

        return new JsonResult<>(sysMenuService.updateObject(sysMenu));
    }
}
