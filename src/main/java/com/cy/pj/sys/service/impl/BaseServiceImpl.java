package com.cy.pj.sys.service.impl;

import com.cy.pj.sys.config.PageProperties;
import com.cy.pj.sys.dao.BaseDao;
import com.cy.pj.sys.entity.AbstractObject;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.BaseService;
import com.cy.pj.sys.util.Assert;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@NoArgsConstructor
public abstract class BaseServiceImpl<T extends AbstractObject, D extends BaseDao<T>> implements BaseService<T> {

    private D dao;

    private String tableName;

    @Autowired
    private PageProperties pageProperties;

    public BaseServiceImpl(D dao,String tableName) {
        this.dao = dao;
        this.tableName = tableName;
    }

    @Override
    public int saveObject(@Validated T t) {
        return dao.insertObject(t);
    }

    @Override
    public int deleteObject(Integer id) {
        Assert.isNull(id, "id值非法");
        return dao.deleteObjectById(tableName, id);
    }

    @Override
    public int updateObject(@Validated T t) {
        return dao.updateObject(t);
    }

    @Override
    public int deleteObjects(Integer... ids) {
        Assert.isEmpty(ids, "参数非法");
        int row = dao.deleteObjects(tableName, ids);
        Assert.isNull(row, "记录不存在");
        return row;
    }

    @Override
    public PageObject<T> findPageObjects(Object key, Integer pageCurrent) {
        // 校验参数
        Assert.isNull(pageCurrent, "页码值无效");
        // 查询总记录数并校验
        int rowCount = getPageRowCount(key);
        // 查询当前页日志记录数
        int startIndex = (pageCurrent - 1) * pageProperties.getPageSize();
        List<T> records = dao.findPageObjects(key, startIndex, pageProperties.getPageSize());
        // 封装结果并返回
        return new PageObject<T>(pageProperties.getPageSize(), pageCurrent, startIndex, rowCount, records);
    }

    public abstract int getPageRowCount(Object key);

    protected abstract D getDao();
}
