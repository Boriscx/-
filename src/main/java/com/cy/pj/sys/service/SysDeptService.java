package com.cy.pj.sys.service;

import com.cy.pj.sys.entity.SysDept;

import java.util.List;
import java.util.Map;

public interface SysDeptService {

    List<SysDept> findObjects();

    List<Map<String,Object>> doFindZTreeNodes();

}
