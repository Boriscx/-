package com.cy.pj.sys.service;

import com.cy.pj.sys.aspect.annotation.RequestLog;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.pojo.UserMenuVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SysMenuService extends BaseService<SysMenu> {
    Set<UserMenuVo> findMenusByUserId(Integer userId);

    List<UserMenuVo> findMenusByUserId1(Integer userId);
}
