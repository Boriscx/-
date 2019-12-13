package com.cy.pj.sys.service;

import com.cy.pj.sys.entity.AbstractObject;
import com.cy.pj.sys.entity.SysDept;
import com.cy.pj.sys.pojo.PageObject;

import java.util.List;
import java.util.Map;

public interface BaseService<T extends AbstractObject> {

    void saveObject(T t);

    int deleteObject(Integer id);

    int deleteObjects(Integer... ids);

    int updateObject(T t);

    PageObject<T> findPageObjects(Object key, Integer pageCurrent);

    List<T> findObjects();

    List<T> findObjects(Object key);

    List<Map<String,Object>> findMapObjects(Object key);

    List<Map<String,Object>> findMapObjects();

    List<Map<String, Object>> findMapZTree();

}
