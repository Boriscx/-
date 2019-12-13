package com.cy.pj.sys.service.impl;

import com.cy.pj.sys.dao.SysDeptDao;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.entity.SysDept;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysDeptService;
import com.cy.pj.sys.util.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SysDeptServiceImpl extends BaseServiceImpl<SysDept, SysDeptDao> implements SysDeptService {


    private SysDeptDao sysDeptDao;

    private SysUserDao sysUserDao;

    public SysDeptServiceImpl(SysDeptDao sysDeptDao, SysUserDao sysUserDao) {
        super(SysDept.TABLE_NAME);
        this.sysUserDao = sysUserDao;
        this.sysDeptDao = sysDeptDao;
    }

    @Transactional
    @Override
    public int deleteObject(Integer id) {
        Assert.isNull(id, "id值无效");
        Assert.isNoNull(sysDeptDao.getRowCount(SysDept.TABLE_NAME, SysDept.PARENT_ID, id), "该部门有子部门,不能删除");
        Assert.isNoNull(sysUserDao.getRowCount(SysUser.TABLE_NAME, SysUser.DEPT_ID, id), "该部门下有员工,请先删除该部门下员工!");
        return super.deleteObject(id);
    }

    @Override
    public int deleteObjects(Integer... ids) {
        throw new RuntimeException("部门不支持同时删除");
    }

    @Override
    public PageObject<SysDept> findPageObjects(Object name, Integer pageCurrent) {
        throw new RuntimeException("部门暂时不用分页查询");
    }

    @Override
    public List<SysDept> findObjects(Object key) {
        throw new RuntimeException("接口待开发");
    }

    @Override
    public List<Map<String, Object>> findMapObjects(Object key) {
        throw new RuntimeException("接口待开发");
    }

    @Override
    protected SysDeptDao getDao() {
        return sysDeptDao;
    }
}
