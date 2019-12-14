package com.cy.pj.sys.service;


import com.cy.pj.sys.entity.SysLog;
import org.springframework.scheduling.annotation.Async;

public interface SysLogService extends BaseService<SysLog> {

    void deleteObjects(Integer... ids);

//    int saveObject(SysLog sysLog);

//    @RequestLog("删除日志")
//    int deleteObjects(Integer... ids);

//    PageObject<SysLog> findPageObject(String username, Integer pageCurrent);


}

