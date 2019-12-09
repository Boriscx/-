package com.cy.sys.log.dao;

import com.cy.pj.sys.dao.SysDeptDao;
import com.cy.pj.sys.entity.SysDept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class SysDeptDaoTest {
    @Autowired
    private SysDeptDao sysDeptDao;

    @Test
    void testFindObjects() {
        List<Map<String,Object>> depts = sysDeptDao.findObjects();
        System.out.println(Arrays.deepToString(depts.toArray()));

    }
}
