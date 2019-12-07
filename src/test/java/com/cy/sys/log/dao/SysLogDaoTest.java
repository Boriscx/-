package com.cy.sys.log.dao;

import com.cy.pj.sys.dao.SysLogDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SysLogDaoTest {

    @Autowired
    private SysLogDao sysLogDao;


    @Test
    void testFindPageObject(){
        sysLogDao.findPageObjects(null,0,3);
    }

    @Test
    void testFindByPage(){
        sysLogDao.findByPage(0,3);
    }

    @Test
    void testDeleteObjects(){
        sysLogDao.deleteObjects(100,200,300);
    }

}
