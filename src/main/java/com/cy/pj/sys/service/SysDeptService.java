package com.cy.pj.sys.service;

import com.cy.pj.sys.dao.BaseDao;
import com.cy.pj.sys.entity.SysDept;

import java.util.List;
import java.util.Map;

public interface SysDeptService extends BaseService<SysDept> {

    List<Map<String, Object>> findObjects();

    List<Map<String, Object>> doFindZTreeNodes();



}
