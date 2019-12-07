package com.cy.pj.sys.controller;

import com.cy.pj.sys.aspect.annotation.RequestLog;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.pojo.JsonResult;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/log/")
public class SysLogController {

    private SysLogService sysLogService;

    public SysLogController(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

   // @RequestLog("获取日志信息")
    @RequestMapping("findAll")
    @ResponseBody
    public Object doFindAll(@RequestParam(name = "username", defaultValue = "",required = false) String username,
                            @RequestParam(name = "pageCurrent",defaultValue = "1") Integer page) {
        PageObject<SysLog> data = sysLogService.findPageObject(username, page);
        return new JsonResult<>(data);
    }

    @RequestLog("日志管理-删除日志记录")
    @RequestMapping("doDeleteObjects")
    @ResponseBody
    public Object doDeleteObjects(Integer... ids) {
        int rows = sysLogService.deleteObjects(ids);
        return new JsonResult("delete success rows is " + rows);
    }
}
