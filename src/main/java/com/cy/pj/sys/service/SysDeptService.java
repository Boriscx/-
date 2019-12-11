package com.cy.pj.sys.service;

import com.cy.pj.sys.aspect.annotation.RequestLog;
import com.cy.pj.sys.entity.SysDept;

import java.util.List;
import java.util.Map;

public interface SysDeptService {


	@RequestLog("添加部门")
	int saveObject(SysDept sysDept);

	@RequestLog("删除部门")
	int deleteObject(Integer id);

	@RequestLog("更新部门")
	int updateObject(SysDept sysDept);

    List<Map<String,Object>> findObjects();

    List<Map<String,Object>> doFindZTreeNodes();

}
