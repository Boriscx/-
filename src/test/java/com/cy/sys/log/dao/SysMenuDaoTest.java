package com.cy.sys.log.dao;

import com.cy.pj.sys.dao.SysMenuDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
public class SysMenuDaoTest {
    @Autowired
    private SysMenuDao sysMenuDao;


    @Test
    void testFindObjects() {
        long start = System.currentTimeMillis();
        List<Map<String, Object>> objects = sysMenuDao.findMapObjects();
        System.out.println("=================");
        System.out.println("findObject2:" + (System.currentTimeMillis() - start));
    }

    @Test
    void testFindPermissionByUserId() {
        List<String> list = sysMenuDao.findPermissionByUserId(17);
        list.forEach(System.out::println);
    }
}
