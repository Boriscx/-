package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface SysRoleMenuDao {

    int insertObjects(@Param("roleId") Integer roleId, @Param("menuIds") List<Integer> menuIds);

    /**
     * 基于菜单id删除角色和菜单关联数据
     * @param id id
     */
    @Delete("delete from sys_role_menus where menu_id=#{id}")
    void deleteObjectsByMenuId(@Param("id") Integer id);

    /**
     * 基于角色删除角色菜单数据
     * @param id id
     */
    @Delete("delete from sys_role_menus where role_id=#{id}")
    void deleteObjectByRoleId(@Param("id") Integer id);

    @Select("select menu_id from sys_role_menus where role_id = #{roleId}")
    List<Integer> findObjectsByRoleId(Integer roseId);

}
