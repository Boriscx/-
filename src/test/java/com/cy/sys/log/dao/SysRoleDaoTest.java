package com.cy.sys.log.dao;

import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.entity.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SysRoleDaoTest {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Test
    void testGetById(){
        SysRole role = sysRoleDao.getObjectById(49);
        System.out.println(role);
    }

}
