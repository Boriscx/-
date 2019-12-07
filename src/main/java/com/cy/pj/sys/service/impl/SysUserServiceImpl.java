package com.cy.pj.sys.service.impl;

import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    private SysUserDao sysUserDao;

    public SysUserServiceImpl(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    @Override
    public PageObject<SysUser> findObjects(String username, Integer currentPage, Integer pageSize) {
        if (currentPage == null || currentPage < 1) throw new IllegalArgumentException("非法页码参数");
        int currentIndex = (currentPage - 1) * pageSize;
        int rowCount = sysUserDao.getRowCount(username);
        if (rowCount<0) throw new RuntimeException("没有记录");
        List<SysUser> data = sysUserDao.findObjects(username,currentIndex,pageSize);
        return new PageObject<SysUser>(pageSize,currentPage,currentIndex,rowCount,data);
    }
}
