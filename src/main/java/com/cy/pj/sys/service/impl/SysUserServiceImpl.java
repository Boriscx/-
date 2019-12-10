package com.cy.pj.sys.service.impl;

import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysDept;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysUserService;
import com.cy.pj.sys.util.Assert;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

@Service
public class SysUserServiceImpl implements SysUserService {

    private SysUserDao sysUserDao;
    private SysUserRoleDao sysUserRoleDao;

    public SysUserServiceImpl(SysUserDao sysUserDao, SysUserRoleDao sysUserRoleDao) {
        this.sysUserDao = sysUserDao;
        this.sysUserRoleDao = sysUserRoleDao;
    }


    @Transactional()
    @Override
    public int saveObject(SysUser sysUser) {
        Assert.isNull(sysUser, "用户信息不能为空");
        Assert.isNull(sysUser.getUsername(), "用户名不能为空");
        Assert.isNull(sysUser.getPassword(), "密码不能为空");
        Assert.isValid(sysUser.getPassword().length() > 6, "密码6~16位");
        if (isHave(sysUser)) throw new RuntimeException("用户名已存在!");

        //密码加密
        String salt = UUID.randomUUID().toString();
//        String newPassword = DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes());  // md5加密
        SimpleHash sh = new SimpleHash("MD5",//算法名称
                sysUser.getPassword(), //未加密的密码
                salt,//盐值
                1);//加密次数
        String newPassword = sh.toHex();//将加密数据转为16进制
        sysUser.setPassword(newPassword);// 重新保存
        sysUser.setSalt(salt);//保存盐值


        int row = sysUserDao.insertObject(sysUser);
        if (row < 1) throw new RuntimeException("注册失败");
        if (sysUser.getRoleIds() != null && sysUser.getRoleIds().length > 0)
            sysUserRoleDao.insertObjectS(sysUser.getId(), sysUser.getRoleIds());
        return row;
    }

    @Transactional
    @Override
    public int updateObject(SysUser sysUser) {
        Assert.isNull(sysUser, "用户信息不能为空");
        Assert.isNull(sysUser.getId(), "用户信息为空");
        Assert.isNull(sysUser.getUsername(), "用户名不能为空");
        int row = sysUserDao.updateObject(sysUser);
        if (row < 1) throw new RuntimeException("用户信息不存在");
        sysUserRoleDao.deleteObjectsByUserId(sysUser.getId());
        sysUserRoleDao.insertObjectS(sysUser.getId(), sysUser.getRoleIds());
        return row;
    }

    @Override
    public int updateValidById(Integer id, Integer valid) {
        Assert.isNull(id, "id值无效");
        Assert.isValid(!(valid != null && (valid == 1 || valid == 0)), "参数非法");
        int row = sysUserDao.updateValidById(id, valid);
        Assert.isNull(row, "角色不存在");
        return row;
    }

    @Override
    public PageObject<SysUser> findObjects(String username, Integer currentPage, Integer pageSize) {
        Assert.isNull(currentPage, "非法页码参数");
        int currentIndex = (currentPage - 1) * pageSize;
        int rowCount = sysUserDao.getRowCount(SysUser.TABLE_NAME, SysUser.USERNAME,username);
        if (rowCount < 0) throw new RuntimeException("没有记录");
        List<SysUser> data = sysUserDao.findPageObjects(username, currentIndex, pageSize);
        return new PageObject<>(pageSize, currentPage, currentIndex, rowCount, data);
    }

    @Override
    public boolean isHave(SysUser sysUser) {
        if (sysUser == null || sysUser.getUsername() == null || sysUser.getUsername().isEmpty())
            throw new IllegalArgumentException("用户信息不能为空");
        return sysUserDao.getRowCount(SysUser.TABLE_NAME,SysUser.USERNAME,sysUser.getUsername()) >0;
    }

    @Override
    public SysUser findObjectById(Integer id) {
        Assert.isNull(id, "id值不能为空");
        SysUser sysUser = sysUserDao.findUserRoleById(id);
        Assert.isNull(sysUser, "用户不存在");
        return sysUser;
    }
}
