package com.cy.pj.sys.service.impl;

import com.cy.pj.sys.aspect.annotation.RequestLog;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.pojo.UserMenuVo;
import com.cy.pj.sys.service.SysMenuService;
import com.cy.pj.sys.util.Assert;
import com.cy.pj.sys.util.ShiroUntil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ClientInfoStatus;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, SysMenuDao> implements SysMenuService {

    private SysMenuDao sysMenuDao;
    private SysRoleMenuDao sysRoleMenuDao;

    public SysMenuServiceImpl(SysMenuDao sysMenuDao, SysRoleMenuDao sysRoleMenuDao) {
        super(SysMenu.TABLE_NAME);
        this.sysMenuDao = sysMenuDao;
        this.sysRoleMenuDao = sysRoleMenuDao;
    }

    @Transactional
    @RequestLog
    @Override
    public int deleteObject(Integer id) {
        // 1.判定参数有效性
        Assert.isNull(id, "id值无效");
        // 2.获取子菜单是否存在,并进行检验
        Assert.isNoNull(sysMenuDao.getRowCount(SysMenu.TABLE_NAME, SysMenu.PARENT_ID, id), "请先删除子菜单");
        // 删除角色菜单关联数据
//        sysRoleMenuDao.deleteObjectsByMenuId(id);
        Assert.isNoNull(sysRoleMenuDao.getRowsCount("menu_id", id), "存在角色关联此菜单,不能删除");
//        sysRoleMenuDao.deleteObjectByColumn("menu_id",id);
        //删除菜单自身数据
        return super.deleteObject(id);
    }

    @Override
    public List<SysMenu> findObjects(Object key) {
        List<SysMenu> data = sysMenuDao.findObjectsByValue(SysMenu.TABLE_NAME, SysMenu.NAME, key);
        Assert.isNull(data, "没有数据");
        return data;
    }

//    @Override
//    public List<Map<String, Object>> findZTreeMap() {
//
//    }

    @Override
    public Set<UserMenuVo> findMenusByUserId(Integer userId) {
        Assert.isNull(userId, "id值非法");
        Set<UserMenuVo> menus = sysMenuDao.findZTreeMapByUserId(userId);

        return menus;
    }

    @Override
    public List<UserMenuVo> findMenusByUserId1(Integer userId) {
        List<UserMenuVo> menuVos = sysMenuDao.findMenusByUserId(userId, false);
        menuVos.forEach((menu) -> {
            menu.setChildMenus(sysMenuDao.findMenusByUserId(userId, true));
        });
        return menuVos;

    }

    @Override
    public int getPageRowCount(Object key) {
        return sysMenuDao.getRowCount(SysMenu.TABLE_NAME, SysMenu.NAME, key);
    }

    @Override
    protected SysMenuDao getDao() {
        return sysMenuDao;
    }

}
