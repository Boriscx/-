package com.cy.pj.sys.util;

import com.cy.pj.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;

public class ShiroUntil {

    public static  String getUsername(){
        return getUser().getUsername();
    }

    public static SysUser getUser(){
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

}
