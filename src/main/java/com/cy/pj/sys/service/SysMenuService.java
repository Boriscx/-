package com.cy.pj.sys.service;

import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.pojo.Node;

import java.util.List;
import java.util.Map;

public interface SysMenuService {

    int saveObject(SysMenu sysMenu);

    int deleteObject(Integer id);

    int updateObject(SysMenu sysMenu);

    List<SysMenu> findObjects();

    List<Node> findZtreeMenuNodes();
}
