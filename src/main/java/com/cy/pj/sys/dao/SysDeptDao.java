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

    int getChildCountById(Integer id);

    List<Map<String,Object>> findObjects();

    List<Map<String,Object>> findNodeObjects();
}
