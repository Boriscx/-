package com.cy.pj.sys.service;

import com.cy.pj.sys.aspect.annotation.RequestLog;

public interface BaseService<T> {
    @RequestLog("添加部门")
    int saveObject(T t);

    @RequestLog("删除部门")
    int deleteObject(Integer id);

    @RequestLog("更新部门")
    int updateObject(T t);
}
