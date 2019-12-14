package com.cy.pj.sys.service.impl;

import com.cy.pj.sys.aspect.annotation.RequestLog;
import com.cy.pj.sys.config.PageProperties;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysRoleService;
import com.cy.pj.sys.util.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, SysRoleDao> implements SysRoleService {

    private SysRoleDao sysRoleDao;

    private SysRoleMenuDao sysRoleMenuDao;

    private SysUserRoleDao sysUserRoleDao;

    public SysRoleServiceImpl(SysRoleDao sysRoleDao, SysRoleMenuDao sysRoleMenuDao, SysUserRoleDao sysUserRoleDao) {
        super(SysRole.TABLE_NAME);
        this.sysRoleDao = sysRoleDao;
        this.sysRoleMenuDao = sysRoleMenuDao;
        this.sysUserRoleDao = sysUserRoleDao;
    }


    @RequestLog
    @Override
    public void updateObject(SysRole sysRole) {
        super.updateObject(sysRole);
        sysRoleMenuDao.deleteObjectByColumn("role_id", sysRole.getId());
        sysRoleMenuDao.insertObjects(sysRole.getId(), sysRole.getMenuIds());
    }

    @Override
    public List<SysRole> findObjects(Object key) {
        return sysRoleDao.findObjectsByValue(SysRole.TABLE_NAME,"name",key);
    }

    @Transactional
    @RequestLog
    @Override
    public void saveObject(SysRole sysRole) {
        Assert.isValid(nameIsHas(sysRole.getName()),"角色名已存在");
        int row = sysRoleDao.insertObject(sysRole);
        Assert.isNull(row,"添加失败");
        if (sysRole.getMenuIds() != null && sysRole.getMenuIds().size() > 0) {
            sysRoleMenuDao.insertObjects(sysRole.getId(), sysRole.getMenuIds());
        }
    }

    @Transactional
    @RequestLog("删除角色")
    @Override
    public int deleteObject(Integer id) {
        Assert.isNull(id, "id值无效");
        sysRoleMenuDao.deleteObjectByColumn("role_id", id);
        Assert.isNoNull(sysUserRoleDao.getRowCountByColumn("role_id", id), "不能删除角色,有用户使用此角色");
        Assert.isNull(sysRoleDao.deleteObjectById(SysRole.TABLE_NAME, id), "数据可能不存在了");
        return 0;
    }

    @Override
    public int getPageRowCount(Object key) {
        return sysRoleDao.getRowCount(SysRole.TABLE_NAME,SysRole.NAME,key);
    }

    @Override
    protected SysRoleDao getDao() {
        return sysRoleDao;
    }
}
