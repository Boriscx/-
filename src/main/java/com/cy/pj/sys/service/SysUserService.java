package com.cy.pj.sys.service;

import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.pojo.PageObject;


public interface SysUserService  {

    int saveObject(SysUser sysUser);

    int updateObject(SysUser sysUser);

    boolean isHave(SysUser sysUser);

    PageObject<SysUser> findObjects(String username, Integer currentIndex, Integer pageSize);

    SysUser findObjectById(Integer id);
}
