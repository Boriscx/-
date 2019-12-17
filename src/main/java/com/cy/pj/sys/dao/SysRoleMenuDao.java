package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysRoleMenuDao {

    int insertObjects(@Param("roleId") Integer roleId, @Param("menuIds") List<Integer> menuIds);

    @Delete("delete from sys_role_menus where ${column}=#{id}")
    void deleteObjectByColumn(@Param("column") String column, @Param("id") Object id);

    @Select("select menu_id from sys_role_menus where role_id = #{roleId}")
    List<Integer> findObjectsByRoleId(Integer roseId);

    @Select("SELECT COUNT(*) FROM sys_role_menus where ${valueName} = #{key}")
    int getRowsCount(@Param("valueName") String valueName, @Param("key") Object key);

    List<Integer> findMenuIdsByRoleIds(@Param("ids") Integer[] ids);

}
