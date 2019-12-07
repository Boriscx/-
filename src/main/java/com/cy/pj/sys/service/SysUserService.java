package com.cy.pj.sys.service;

import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.pojo.PageObject;

import java.util.Map;

public interface SysUserService  {

    PageObject<SysUser> findObjects(String username, Integer currentIndex, Integer pageSize);

}
