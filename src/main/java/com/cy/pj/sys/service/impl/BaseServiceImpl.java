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
import java.util.Map;

@NoArgsConstructor
public abstract class BaseServiceImpl<T extends AbstractObject, D extends BaseDao<T>> implements BaseService<T> {


    private String tableName;

    @Autowired
    private PageProperties pageProperties;

    public BaseServiceImpl(String tableName) {
        this.tableName = tableName;
    }

    public BaseServiceImpl(D dao, String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void saveObject(@Validated T t) {
        Assert.isNull(t, "不能为空!");
        int row = getDao().insertObject(t);
        Assert.isNull(row, "保存失败");
    }

    @Override
    public int deleteObject(Integer id) {
        Assert.isNull(id, "id值非法");
        return getDao().deleteObjectById(tableName, id);
    }

    @Override
    public int updateObject(@Validated T t) {
        Assert.isNull(t.getId(), "id值非法");
        return getDao().updateObject(t);
    }

    @Override
    public int deleteObjects(Integer... ids) {
        Assert.isEmpty(ids, "参数非法");
        int row = getDao().deleteObjects(tableName, ids);
        Assert.isNull(row, "记录不存在");
        return row;
    }

    @Override
    public PageObject<T> findPageObjects(Object key, Integer pageCurrent) {
        // 校验参数
        Assert.isNull(pageCurrent, "页码值无效");
        // 查询总记录数并校验
        int rowCount = getPageRowCount(key);
        Assert.isNull(rowCount, "没有记录");
        // 查询当前页日志记录数
        int startIndex = (pageCurrent - 1) * pageProperties.getPageSize();
        List<T> records = getDao().findPageObjects(key, startIndex, pageProperties.getPageSize());
        // 封装结果并返回
        return new PageObject<T>(pageProperties.getPageSize(), pageCurrent, startIndex, rowCount, records);
    }

    @Override
    public List<T> findObjects() {
        return getDao().findObjects(tableName);
    }

    @Override
    public List<T> findObjects(Object key) {
        return findObjects();
    }

    public List<T> findObjects(String valueName, Object key) {
        return findObjects();
    }

    @Override
    public List<Map<String, Object>> findMapObjects(Object key) {
        List<Map<String, Object>> data = getDao().findMapObjects();
        Assert.isNull(data, "没有记录");
        return data;
    }

    @Override
    public List<Map<String, Object>> findMapObjects() {
        return getDao().findMapObjects();
    }

    @Override
    public List<Map<String, Object>> findMapZTree() {
        List<Map<String, Object>> data = getDao().findZTreeMap();
        Assert.isNull(data, "没有对应数据");
        return data;
    }

    public int getPageRowCount(Object key) {
        return -1;
    }

    ;

    protected abstract D getDao();

}
