package com.cy.sys.log;

import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BeanTest {


    @Autowired
    private SysUserService sysUserService;

    @Test
    void testBean(){
        sysUserService.updateValidById(200,1);
    }

}
