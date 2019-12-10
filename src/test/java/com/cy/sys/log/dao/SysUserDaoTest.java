package com.cy.sys.log.dao;

import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class SysUserDaoTest {

    @Autowired
    private SysUserDao sysUserDao;

    @Test
    void testFindObjects(){
        List<SysUser> users = sysUserDao.findPageObjects(null, 0, 10);
        System.out.println(users);
    }

}
