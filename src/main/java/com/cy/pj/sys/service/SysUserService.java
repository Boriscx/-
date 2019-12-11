package com.cy.pj.sys.service;

import com.cy.pj.sys.aspect.annotation.RequestLog;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.pojo.PageObject;


public interface SysUserService  {

    @RequestLog("添加用户")
    int saveObject(SysUser sysUser);

    @RequestLog("修改用户")
    int updateObject(SysUser sysUser);

    @RequestLog("修改用户状态(启用/禁用)")
    int updateValidById(Integer id,Integer valid);

    boolean isHave(SysUser sysUser);

    PageObject<SysUser> findObjects(String username, Integer currentIndex, Integer pageSize);

    SysUser findObjectById(Integer id);
}
