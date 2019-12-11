package com.cy.pj.sys.service;

import com.cy.pj.sys.aspect.annotation.RequestLog;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.pojo.Node;

import java.util.List;
import java.util.Map;

public interface SysMenuService {

    @RequestLog("添加菜单")
    int saveObject(SysMenu sysMenu);

    @RequestLog("删除菜单")
    int deleteObject(Integer id);

    @RequestLog("修改菜单")
    int updateObject(SysMenu sysMenu);

    List<SysMenu> findObjects();

    List<Node> findZtreeMenuNodes();
}
