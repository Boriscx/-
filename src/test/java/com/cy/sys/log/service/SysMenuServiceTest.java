package com.cy.sys.log.service;

import com.cy.pj.sys.pojo.UserMenuVo;
import com.cy.pj.sys.service.SysMenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class SysMenuServiceTest {

    @Autowired
    private SysMenuService sysMenuService;

    @Test
    void testFindMenusByUserId(){
        //List<UserMenuVo> menusByUserId1 = sysMenuService.findMenusByUserId1(17);
        //Set<UserMenuVo> menuVos = new HashSet<>(menusByUserId1);
        //menuVos.forEach(System.out::println);
    }

}
