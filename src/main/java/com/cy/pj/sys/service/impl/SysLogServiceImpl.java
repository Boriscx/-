package com.cy.pj.sys.service.impl;

import com.cy.pj.sys.aspect.annotation.RequestLog;
import com.cy.pj.sys.config.PageProperties;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysLogService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 思考:业务层对象要处理哪些业务
 * 核心业务:
 * 拓展业务:
 */

@Service
public class SysLogServiceImpl implements SysLogService {

    private final SysLogDao sysLogDao;

    private final PageProperties pageProperties;

    public SysLogServiceImpl(SysLogDao sysLogDao, PageProperties pageProperties) {
        this.sysLogDao = sysLogDao;
        this.pageProperties = pageProperties;
    }

    @Override
    public int saveObject(SysLog sysLog) {
        return sysLogDao.insertObject(sysLog);
    }


    @Override
    public PageObject<SysLog> findPageObject(String username, Integer pageCurrent) {
        // 校验参数
        // PageUtil.isValid(pageCurrent);
        if (pageCurrent==null||pageCurrent<1) throw new IllegalArgumentException("页码值无效");
//        if (pageSize == null || pageSize < 1) throw new IllegalArgumentException("每页记录数无效");
        // 查询总记录数并校验
        int rowCount = sysLogDao.getRowCount(username);
        if (rowCount == 0) throw new RuntimeException("记录不存在");
        /* 查询当前页日志记录数 */
        int startIndex = (pageCurrent - 1) * pageProperties.getPageSize();
        List<SysLog> records = sysLogDao.findPageObjects(username, startIndex, pageProperties.getPageSize());
        // 封装结果并返回
        return new PageObject<SysLog>(pageProperties.getPageSize(), pageCurrent, startIndex, rowCount, records);
    }

    @Override
    public int deleteObjects(Integer... ids) {
        if (ids==null||ids.length==0) throw new IllegalArgumentException("请先选择");//非法参数异常
//        System.out.println("service:"+Arrays.toString(ids));
//        Assert.isTrue(ids != null && ids.length > 0, "请先选择");

        int rows = sysLogDao.deleteObjects(ids);
        if (rows == 0) throw new RuntimeException("记录可能不存在");
        return rows;

    }
}
