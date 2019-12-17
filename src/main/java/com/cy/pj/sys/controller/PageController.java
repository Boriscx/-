package com.cy.pj.sys.controller;

import com.cy.pj.sys.aspect.annotation.RequestLog;
import org.apache.shiro.authz.aop.UserAnnotationHandler;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/")
public class PageController {

    private final AtomicLong atomicLong = new AtomicLong(0);

    @RequestMapping("/")
    public String doIndexUI() {
        atomicLong.incrementAndGet();
        System.out.println(atomicLong);
        return "starter";
    }

    /**
     * rest风格(一个软件架构编码编码风格)的url映射
     * {} 为rest表达式
     * -@PathVariable用于告诉springMVC参数为一个rest变量
     *
     * @param moduleUI moduleUI
     * @param module   module
     * @return "sys"/moduleUI
     */
    @RequestMapping("{module}/{moduleUI}")
    public String doModuleUI(@PathVariable String moduleUI, @PathVariable String module) {
        return "sys/" + moduleUI;
    }

    @RequestMapping("/{moduleUI}")
    public String doModuleUI(@PathVariable String moduleUI) {
        return moduleUI;
    }

    @RequestMapping("doPageUI")
    public String doPageUI() {
        return "common/page";
    }



}
