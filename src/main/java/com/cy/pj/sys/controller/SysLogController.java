package com.cy.pj.sys.controller;

import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.pojo.JsonResult;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysLogService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/log/")
public class SysLogController {

    private SysLogService sysLogService;

    public SysLogController(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    @RequestMapping("findAll")
    public Object doFindAll(@RequestParam(name = "username", defaultValue = "", required = false) String username,
                            @RequestParam(name = "pageCurrent", defaultValue = "1") Integer page) {
        PageObject<SysLog> data = sysLogService.findPageObjects(username, page);
        return new JsonResult<>(data);
    }

    @PostMapping("doDeleteObjects")
    public Object doDeleteObjects(Integer... ids) {
        sysLogService.deleteObjects(ids);
        return new JsonResult("delete success ");
    }
}
