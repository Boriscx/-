package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface SysRoleMenuDao {

    int insertObjects(@Param("roleId") Integer roleId, @Param("menuIds") List<Integer> menuIds);

    @Select("delete from sys_role_menus where ${column}=#{id}")
    void deleteObjectByColumn(@Param("column") String column, @Param("id") Object id);

    @Select("select menu_id from sys_role_menus where role_id = #{roleId}")
    List<Integer> findObjectsByRoleId(Integer roseId);

}
