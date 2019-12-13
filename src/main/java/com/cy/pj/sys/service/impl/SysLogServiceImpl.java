package com.cy.pj.sys.service.impl;

import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.SysLogService;
import com.cy.pj.sys.util.Assert;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 思考:业务层对象要处理哪些业务
 * 核心业务:
 * 拓展业务:
 */

@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLog,SysLogDao> implements SysLogService {

    private final SysLogDao sysLogDao;

    public SysLogServiceImpl(SysLogDao sysLogDao) {
        super(SysLog.TABLE_NAME);
        this.sysLogDao = sysLogDao;
    }

    @Async
    @Override
    public void saveObject(SysLog sysLog) {
        super.saveObject(sysLog);
    }

    @Override
    public int updateObject(SysLog sysLog) {
        throw new RuntimeException("日志不允许修改");
    }

    @Override
    public int getPageRowCount(Object key) {
        return sysLogDao.getRowCount(SysLog.TABLE_NAME, SysLog.USERNAME, key);
    }

    @Override
    protected SysLogDao getDao() {
        return sysLogDao;
    }
}
