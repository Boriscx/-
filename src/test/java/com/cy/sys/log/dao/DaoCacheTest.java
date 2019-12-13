package com.cy.sys.log.dao;

import com.cy.pj.sys.entity.SysRole;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class DaoCacheTest {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    void testSecondCache() {

        SqlSession sqlSession1 = sqlSessionFactory.openSession();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();

        Object startIndex = "{\"startIndex\":0,\"pageSize\":10}";

        List<SysRole> list1 = sqlSession1.selectList("com.cy.pj.sys.dao.SysRoleDao.testFindObjects");

        sqlSession1.close();

        List<SysRole> list2 = sqlSession2.selectList("com.cy.pj.sys.dao.SysRoleDao.testFindObjects");

        System.out.println("list1==list2:" + (list1 == list2));

        sqlSession2.close();

        list1.clear();

        System.out.println("list2:"+ Arrays.toString(list2.toArray()));

    }


}
