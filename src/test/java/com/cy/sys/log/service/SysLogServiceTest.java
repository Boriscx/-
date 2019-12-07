package com.cy.sys.log.service;

import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SysLogServiceTest {

    @Autowired
    private SysLogService sysLogService;

    @Test
    void testFindPageObject(){
        PageObject<SysLog> page = sysLogService.findPageObject("bcx", -1);
        System.out.println(page);
    }
}
