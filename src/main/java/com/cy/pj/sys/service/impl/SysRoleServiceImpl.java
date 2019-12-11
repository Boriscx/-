package com.cy.pj.sys.service.impl;

import com.cy.pj.sys.config.PageProperties;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysRoleService;
import com.cy.pj.sys.util.Assert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    private SysRoleDao sysRoleDao;

    private PageProperties pageProperties;

    private SysRoleMenuDao sysRoleMenuDao;

    private SysUserRoleDao sysUserRoleDao;

    public SysRoleServiceImpl(SysRoleDao sysRoleDao, PageProperties pageProperties, SysRoleMenuDao sysRoleMenuDao, SysUserRoleDao sysUserRoleDao) {
        this.sysRoleDao = sysRoleDao;
        this.pageProperties = pageProperties;
        this.sysRoleMenuDao = sysRoleMenuDao;
        this.sysUserRoleDao = sysUserRoleDao;
    }


    @Override
    public int updateObject(SysRole sysRole) {
        Assert.isNull(sysRole, "角色信息为空");
        Assert.isEmpty(sysRole.getName(), "角色名不能为空");
        SysRole r = sysRoleDao.getObjectByColumn(SysRole.TABLE_NAME, SysRole.NAME, sysRole.getName());
        Assert.isNull(r, "角色不存在");
        int row = sysRoleDao.updateObject(sysRole);
        if (row > 0) {
            sysRoleMenuDao.deleteObjectByRoleId(sysRole.getId());
            sysRoleMenuDao.insertObjects(sysRole.getId(), sysRole.getMenuIds());
        }
        return row;
    }

    @Override
    public int saveObject(SysRole sysRole) {
        Assert.isNull(sysRole, "角色信息为空");
        Assert.isEmpty(sysRole.getName(), "角色名不能为空");
        SysRole r = sysRoleDao.getObjectByColumn(SysRole.TABLE_NAME, SysRole.NAME, sysRole.getName());
        Assert.isNoNull(r, "角色已经存在");
        int row = sysRoleDao.insertObject(sysRole);
        System.out.println("role id is " + sysRole.getId());
        if (row > 0 && sysRole.getMenuIds() != null && sysRole.getMenuIds().size() > 0) {
            sysRoleMenuDao.insertObjects(sysRole.getId(), sysRole.getMenuIds());
        }
        return row;
    }

    // 删除
    @Override
    public int deleteObject(Integer id) {
        if (id == null || id < 1) throw new IllegalArgumentException("id值无效");
        sysRoleMenuDao.deleteObjectByRoleId(id);
        sysUserRoleDao.deleteObjectsByRoleId(id);
        int row = sysRoleDao.deleteObjectById("sys_roles", id);
        if (row < 1) throw new RuntimeException("数据不存在了");
        return row;
    }

    @Override
    public SysRole getObjectById(Integer id) {
        Assert.isNull(id, "id无效");
        SysRole sysRole = sysRoleDao.getMapById(id);
        Assert.isNull(sysRole, "角色不存在!");
        return sysRole;
    }

    //查找
    @Override
    public PageObject<SysRole> findPageObject(String name, Integer pageCurrent, Integer pageSize) {
        if (pageCurrent == null || pageCurrent < 1) throw new IllegalArgumentException("页码异常!");
        int rowCount = sysRoleDao.getRowCount(SysRole.TABLE_NAME, SysRole.NAME, name);
        if (rowCount < 1) throw new RuntimeException("没有记录");
        if (pageSize == null || pageSize < 10)
            pageSize = pageProperties.getPageSize();
        int startIndex = (pageCurrent - 1) * pageSize;
        return new PageObject<>(pageSize, pageCurrent, startIndex, rowCount, sysRoleDao.findPageObjects(name, startIndex, pageSize));

    }

    @Override
    public List<Map<String, Object>> findMapObjects() {
        return sysRoleDao.findMapObjects();
    }
}
