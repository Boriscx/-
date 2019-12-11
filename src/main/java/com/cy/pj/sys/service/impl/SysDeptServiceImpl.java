package com.cy.pj.sys.service.impl;

import com.cy.pj.sys.dao.SysDeptDao;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.entity.SysDept;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysDeptService;
import com.cy.pj.sys.util.Assert;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SysDeptServiceImpl implements SysDeptService {


    private SysDeptDao sysDeptDao;

    private SysUserDao sysUserDao;

    private SqlSession sqlSession;

    public SysDeptServiceImpl(SqlSession sqlSession,SysDeptDao sysDeptDao, SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
        this.sysDeptDao = sysDeptDao;
        this.sqlSession = sqlSession;
    }

    public int saveObject(SysDept sysDept) {
        Assert.isNull(sysDept, "部门信息为空");
        Assert.isEmpty(sysDept.getName(), "部门名称不能为空!");
        return sysDeptDao.insertObject(sysDept);
    }

    @Override
    @Transactional
    public int deleteObject(Integer id) {
        Assert.isNull(id, "id值无效");
        Assert.isNoNull(sysDeptDao.getRowCount(SysDept.TABLE_NAME,SysDept.PARENT_ID,id), "该部门有子部门,不能删除");
        Assert.isNoNull(sysUserDao.getRowCount(SysUser.TABLE_NAME, SysUser.DEPT_ID, id), "该部门下有员工,请先删除该部门下员工!");
        int row = sysDeptDao.deleteObjectById(SysDept.TABLE_NAME, id);
        if (row < 1) throw new RuntimeException("删除失败!部门不存在!");
        sqlSession.clearCache();
        System.out.println("已经删除了部门:"+id);
        return row;
    }

    @Override
    public int updateObject(SysDept sysDept) {
        Assert.isNull(sysDept, "部门信息为空");
        Assert.isNull(sysDept.getName(), "部门名称不能为空!");
        return sysDeptDao.updateObject(sysDept);
    }

    @Override
    public List<Map<String, Object>> findObjects() {
        List<Map<String, Object>> data = sysDeptDao.findMapObjects();
        Assert.isEmpty(data, "没有对应数据");
        return data;
    }

    @Override
    public List<Map<String, Object>> doFindZTreeNodes() {
        List<Map<String, Object>> data = sysDeptDao.findNodeObjects();
        Assert.isEmpty(data, "没有对应数据");
        return data;
    }

    @Override
    public int deleteObjects(Integer... ids) {
        return 0;
    }

    @Override
    public PageObject<SysDept> findPageObjects(Object username, Integer pageCurrent) {
        throw new RuntimeException("");
    }

}
