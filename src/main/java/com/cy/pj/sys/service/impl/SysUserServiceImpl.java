package com.cy.pj.sys.service.impl;

import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysUserService;
import com.cy.pj.sys.util.Assert;
import com.cy.pj.sys.util.ShiroUntil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @RequiresPermissions("sys:user:add")
    @Transactional()
    @Override
    public int saveObject(SysUser sysUser) {
        Assert.isNull(sysUser.getPassword(),"密码不能为空");
        //密码加密
        String salt = UUID.randomUUID().toString();
//        String newPassword = DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes());  // md5加密
        SimpleHash sh = new SimpleHash("MD5",//算法名称
                sysUser.getPassword(), //未加密的密码
                salt,//盐值
                1);//加密次数
        String newPassword = sh.toHex();//将加密数据转为16进制
        String username = ShiroUntil.getUsername();
        sysUser.setCreatedUser(username);
        sysUser.setModifiedUser(username);
        sysUser.setPassword(newPassword);// 重新保存
        sysUser.setSalt(salt);//保存盐值

        int row = sysUserDao.insertObject(sysUser);
        if (row < 1) throw new RuntimeException("注册失败");
        if (sysUser.getRoleIds() != null && sysUser.getRoleIds().size() > 0)
            sysUserRoleDao.insertObjectS(sysUser.getId(), sysUser.getRoleIds());
        return row;
    }

    @Transactional
    @Override
    public int updateObject(SysUser sysUser) {
        Assert.isNull(sysUser, "用户信息不能为空");
        Assert.isNull(sysUser.getId(), "用户信息为空");
        Assert.isNull(sysUser.getUsername(), "用户名不能为空");
        sysUser.setModifiedUser(ShiroUntil.getUsername());
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
    public void updatePassword(String password, String newPassword, String cfgPassword) {

        // 校验原密码
        Assert.isNull(password,"原密码不能为空");
        Assert.isNull(newPassword,"新密码不能为空");
        // 获取当前登录用户信息
        SysUser sysUser = ShiroUntil.getUser();
        Assert.isNull(sysUser,"请先登录!");
        // 校验新密码两次输入是否合格
        Assert.isValid(!newPassword.equals(cfgPassword),"两次密码输入不一致");
            // 加密原密码
        String salt = ShiroUntil.getUser().getSalt();
        SimpleHash sh = new SimpleHash("MD5",password,salt,1);
        String s = sh.toHex();
            // 比对密码
        Assert.isValid(!s.equals(ShiroUntil.getUser().getPassword()),"原密码不正确");
        // 生成新的盐值
        String newSalt = UUID.randomUUID().toString();
        sh = new SimpleHash("MD5",newPassword,newSalt,1);
        // 保存修改信息
        sysUserDao.updatePassword(sh.toHex(),newSalt,ShiroUntil.getUser().getId());
        SecurityUtils.getSubject().logout();
        // 验证是否修改成功


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

    @Override
    public boolean isExists(String columnName, String columnValue) {
        SysUser objectByColumn = sysUserDao.getObjectByColumn(SysUser.TABLE_NAME, columnName, columnValue);
        Assert.isNoNull(objectByColumn,"用户名已存在");
        return false;
    }

}
