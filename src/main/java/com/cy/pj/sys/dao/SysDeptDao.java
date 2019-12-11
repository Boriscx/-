package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysDept;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface SysDeptDao extends BaseDao<SysDept> {

    @Override
    @Insert("INSERT INTO sys_depts(id, name, parentId, sort, note, createdTime, modifiedTime, createdUser, modifiedUser) " +
            "VALUES (null, #{name}, #{parentId}, #{sort}, #{note}, now(), now(), #{createdUser}, #{modifiedUser})")
    int insertObject(SysDept sysDept);

    @Override
    @Select(" SELECT id, name, parentId FROM sys_depts")
    List<Map<String, Object>> findNodeObjects();

    @Override
    @Select("SELECT c.*, p.name parentName FROM sys_depts c LEFT JOIN sys_depts p on c.parentId = p.id")
    List<Map<String, Object>> findMapObjects();
}
