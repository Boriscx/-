package com.cy.pj.sys.service;

import com.cy.pj.sys.entity.AbstractObject;
import com.cy.pj.sys.pojo.PageObject;

public interface BaseService<T extends AbstractObject> {

    int saveObject(T t);

    int deleteObject(Integer id);

    int updateObject(T t);

    int deleteObjects(Integer... ids);

    PageObject<T> findPageObjects(Object key, Integer pageCurrent);
}
