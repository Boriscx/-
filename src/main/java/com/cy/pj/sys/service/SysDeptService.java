package com.cy.pj.sys.service;

import com.cy.pj.sys.entity.SysDept;

import java.util.List;
import java.util.Map;

public interface SysDeptService {


	int saveObject(SysDept sysDept);

	int deleteObject(Integer id);

	int updateObject(SysDept sysDept);

    List<SysDept> findObjects();

    List<Map<String,Object>> doFindZTreeNodes();

}
