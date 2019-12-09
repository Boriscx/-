package com.cy.pj.sys.service.impl;

import com.cy.pj.sys.dao.SysDeptDao;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.entity.SysDept;
import com.cy.pj.sys.service.SysDeptService;
import com.cy.pj.sys.util.Assert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysDeptServiceImpl implements SysDeptService {


    private SysDeptDao sysDeptDao;

    private SysUserDao sysUserDao;

    public SysDeptServiceImpl(SysDeptDao sysDeptDao, SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
        this.sysDeptDao = sysDeptDao;
    }


    public int saveObject(SysDept sysDept) {
        Assert.isNull(sysDept, "部门信息为空");
        Assert.isNull(sysDept.getName(), "部门名称不能为空!");
        return sysDeptDao.insertObject(sysDept);
    }

    @Override
    public int deleteObject(Integer id) {
        Assert.isNull(id, "id值无效");
        Assert.isNoNull(sysDeptDao.getChildCountById(id),"该部门有子部门,不能删除");
        Assert.isNoNull(sysUserDao.findObjectByDeptId(id),"该部门下有员工,请先删除该部门下员工!");
        int row = sysDeptDao.deleteObjects(id);
        if (row < 1) throw new RuntimeException("删除失败!部门不存在!");
        return row;
    }

    @Override
    public int updateObject(SysDept sysDept) {
        Assert.isNull(sysDept, "部门信息为空");
        Assert.isNull(sysDept.getName(), "部门名称不能为空!");
        return sysDeptDao.updateObject(sysDept);
    }

    @Override
    public List<SysDept> findObjects() {
        List<SysDept> data = sysDeptDao.findObjects();
        // 断言 数据空  返回异常
        Assert.isEmpty(data,"没有对应数据");
        return data;
    }

    @Override
    public List<Map<String, Object>> doFindZTreeNodes() {
        List<Map<String, Object>> data = sysDeptDao.findNodeObjects();
        Assert.isEmpty(data,"没有对应数据");
        return data;
    }
}
