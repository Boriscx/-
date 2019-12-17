package com.cy.pj.sys.service.impl;

import com.cy.pj.sys.aspect.annotation.RequestLog;
import com.cy.pj.sys.config.PageProperties;
import com.cy.pj.sys.dao.BaseDao;
import com.cy.pj.sys.entity.AbstractObject;
import com.cy.pj.sys.pojo.PageObject;
import com.cy.pj.sys.service.BaseService;
import com.cy.pj.sys.util.Assert;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@NoArgsConstructor
public abstract class BaseServiceImpl<T extends AbstractObject, D extends BaseDao<T>> implements BaseService<T> {

    private String tableName;

    @Autowired
    private PageProperties pageProperties;

    public BaseServiceImpl(String tableName) {
        this.tableName = tableName;
    }

    @RequestLog("添加")
    @Override
    public void saveObject(@Validated T t) {
        Assert.isNull(t, "不能为空!");
        int row = getDao().insertObject(t);
        Assert.isNull(row, "保存失败");
    }

    @RequestLog("删除")
    @Override
    public int deleteObject(Integer id) {
        Assert.isNull(id, "id值非法");
        int row = getDao().deleteObjectById(tableName, id);
        Assert.isNull(row,"数据可能不存在");
        return row;
    }

    @RequestLog("修改")
    @Override
    public void updateObject(@Validated T t) {
        Assert.isNull(t.getId(), "id值非法");
        Assert.isNull(getDao().updateObject(t), "记录不存在");
    }

    @Override
    public T getObjectById(Integer id) {
        Assert.isNull(id, "id值无效");
        T object = getDao().getObjectById(tableName, id);
        Assert.isNull(object, "没有数据");
        return object;
    }

    @Override
    public T getObjectByName(String name) {
        Assert.isNull(name, "关键词不能为空");
        T obj = getDao().getObjectByColumn(tableName, "name", name);
        Assert.isNull(obj, "没有数据");
        return obj;
    }

    @Override
    public boolean nameIsHas(String name) {
        Assert.isNull(name, "关键词不能为空");
        T obj = getDao().getObjectByColumn(tableName, "name", name);
        return obj != null;
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
        List<T> objects = getDao().findObjects(tableName);
        Assert.isNull(objects, "没有数据");
        return objects;
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
    public List<Map<String, Object>> findZTreeMap() {
        List<Map<String, Object>> data = getDao().findZTreeMap();
        Assert.isNull(data, "没有对应数据");
        return data;
    }

    public abstract int getPageRowCount(Object key);

    protected abstract D getDao();

}
