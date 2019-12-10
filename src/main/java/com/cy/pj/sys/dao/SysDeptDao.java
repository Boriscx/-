package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface SysDeptDao extends BaseDao<SysDept> {

    @Override
    int insertObject(SysDept sysDept);

    @Override
    int updateObject(SysDept sysDept);

    @Override
    List<Map<String, Object>> findMapObjects();

    List<Map<String,Object>> findNodeObjects();

}
