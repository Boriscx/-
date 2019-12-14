package com.cy.pj.sys.service.impl;

import com.cy.pj.sys.aspect.annotation.RequestLog;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
import com.cy.pj.sys.util.Assert;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    public void updateObject(SysLog sysLog) {
        throw new RuntimeException("日志不允许修改");
    }

    @RequestLog
    @Override
    public void deleteObjects(Integer... ids) {
        Assert.isEmpty(ids, "参数非法");
        Assert.isNull(getDao().deleteObjects(SysLog.TABLE_NAME, ids), "记录不存在");
        //return row;
    }

    @Override
    public List<SysLog> findObjects(Object key) {
        return sysLogDao.findObjectsByValue(SysLog.TABLE_NAME,SysLog.USERNAME,key);
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
