package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface SysDeptDao {

    int insertObject(SysDept sysDept);

    int deleteObjects(@Param("ids") Integer... ids);

    int updateObject(SysDept sysDept);

    @Select("SELECT c.id,c.name,p.name parentName,c.sort FROM sys_depts c LEFT JOIN sys_depts p on c.parentId=p.id")
    List<SysDept> findObjects();

    @Select("SELECT c.id,c.name,c.parentId FROM sys_depts c ")
    List<Map<String,Object>> findNodeObjects();
}
