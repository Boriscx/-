package com.cy.pj.sys.service.impl;

import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.pojo.Node;
import com.cy.pj.sys.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    private SysMenuDao sysMenuDao;
    private SysRoleMenuDao sysRoleMenuDao;

    public SysMenuServiceImpl(SysMenuDao sysMenuDao, SysRoleMenuDao sysRoleMenuDao) {
        this.sysMenuDao = sysMenuDao;
        this.sysRoleMenuDao = sysRoleMenuDao;
    }

    @Override
    public int saveObject(SysMenu sysMenu) {
        if (sysMenu == null) throw new RuntimeException("添加数据不能为空!");
        if (sysMenu.getName() == null || sysMenu.getName().isEmpty()) throw new IllegalArgumentException("菜单名称不能为空");
        int row = sysMenuDao.insertObject(sysMenu);
        if (row < 1) throw new RuntimeException("添加失败!");
        return row;
    }

    @Override
    public int deleteObject(Integer id) {
        // 1.判定参数有效性
        if (id == null || id < 1) throw new IllegalArgumentException("id值无效");
        // 2.获取子菜单是否存在,并进行检验
        int childCount = sysMenuDao.getRowCount(SysMenu.TABLE_NAME, SysMenu.PARENT_ID, id);
        if (childCount > 0) throw new RuntimeException("请先删除子菜单");
        // 删除角色菜单关联数据
        sysRoleMenuDao.deleteObjectsByMenuId(id);
        //删除菜单自身数据
        int row = sysMenuDao.deleteObjectById("sys_menus", id);
        if (row < 1) throw new RuntimeException("记录不存在了!");
        // 返回结果
        return row;
    }

    @Override
    public int updateObject(SysMenu sysMenu) {
        if (sysMenu == null) throw new RuntimeException("修改的数据不能为空!");
        if (sysMenu.getName() == null || sysMenu.getName().isEmpty()) throw new IllegalArgumentException("菜单名称不能为空");
        sysMenu.setModifiedUser("bcx");
        sysMenu.setModifiedTime(new Date());
        int row = sysMenuDao.updateObject(sysMenu);
        if (row < 1) throw new RuntimeException("修改失败!");
        return row;
    }

    @Override
    public List<SysMenu> findObjects() {
        List<SysMenu> list = sysMenuDao.findObjects(SysMenu.TABLE_NAME);
        if (list == null || list.size() == 0)
            throw new RuntimeException("没有对应的菜单信息");
        return list;
    }

    @Override
    public List<Node> findZtreeMenuNodes() {
        return sysMenuDao.findZtreeMenuNodes();
    }
}
