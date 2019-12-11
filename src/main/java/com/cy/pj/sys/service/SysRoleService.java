package com.cy.pj.sys.service;

import com.cy.pj.sys.aspect.annotation.RequestLog;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.pojo.PageObject;

import java.util.List;
import java.util.Map;

public interface SysRoleService {

    @RequestLog("添加角色")
    int saveObject(SysRole sysRole);

    @RequestLog("删除角色")
    int deleteObject(Integer id);

    @RequestLog("修改角色")
    int updateObject(SysRole sysRole);

    SysRole getObjectById(Integer id);

    PageObject<SysRole> findPageObject(String name, Integer pageCurrent, Integer pageSize);

    List<Map<String, Object>> findMapObjects();

}
