package com.cy.pj.sys.service;


import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.pojo.PageObject;

public interface SysLogService {

    int saveObject(SysLog sysLog);

    int deleteObjects(Integer... ids);

    PageObject<SysLog> findPageObject(String username, Integer pageCurrent);


}

