package com.cy.pj.sys.service.impl;

import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysUserService;
import com.cy.pj.sys.util.Assert;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    private SysUserDao sysUserDao;

    public SysUserServiceImpl(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }


    @Override
    public int saveObject(SysUser sysUser) {
        if (isHave(sysUser)) throw new RuntimeException("用户名已存在!");
        int row = sysUserDao.insertObject(sysUser);
        if (row < 1) throw new RuntimeException("注册失败");
        return row;
    }

    @Override
    public PageObject<SysUser> findObjects(String username, Integer currentPage, Integer pageSize) {
        Assert.isNull(currentPage,"非法页码参数");
        int currentIndex = (currentPage - 1) * pageSize;
        int rowCount = sysUserDao.getRowCount(username);
        if (rowCount < 0) throw new RuntimeException("没有记录");
        List<SysUser> data = sysUserDao.findObjects(username, currentIndex, pageSize);
        return new PageObject<>(pageSize, currentPage, currentIndex, rowCount, data);
    }

    @Override
    public boolean isHave(SysUser sysUser) {
        if (sysUser == null || sysUser.getUsername() == null || sysUser.getUsername().isEmpty())
            throw new IllegalArgumentException("用户信息不能为空");
        return sysUserDao.getByName(sysUser.getUsername()) != null;
    }
}
