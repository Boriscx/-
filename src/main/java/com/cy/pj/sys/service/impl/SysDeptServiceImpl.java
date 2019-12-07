package com.cy.pj.sys.service.impl;

import com.cy.pj.sys.dao.SysDeptDao;
import com.cy.pj.sys.entity.SysDept;
import com.cy.pj.sys.service.SysDeptService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysDeptServiceImpl implements SysDeptService {


    private SysDeptDao sysDeptDao;

    public SysDeptServiceImpl(SysDeptDao sysDeptDao) {
        this.sysDeptDao = sysDeptDao;
    }

    @Override
    public List<SysDept> findObjects() {
        List<SysDept> data = sysDeptDao.findObjects();
        if (data == null || data.size() == 0) throw new RuntimeException("没有数据");
        return data;
    }

    @Override
    public List<Map<String, Object>> doFindZTreeNodes() {
        List<Map<String, Object>> data = sysDeptDao.findNodeObjects();
        if (data == null || data.size() == 0) throw new RuntimeException("没有数据");
        return data;
    }
}
