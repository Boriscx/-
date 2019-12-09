package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface SysRoleMenuDao {

    int insertObjects(@Param("roleId") Integer roleId, @Param("menuIds") Integer[] menuIds);

    /**
     * 基于菜单id删除角色和菜单关联数据
     * @param id id
     */
    @Delete("delete from sys_role_menus where menu_id=#{id}")
    int deleteObjectsByMenuId(@Param("id") Integer id);

    /**
     * 基于角色删除角色菜单数据
     * @param id id
     * @return 删除记录条数
     */
    @Delete("delete from sys_role_menus where role_id=#{id}")
    int deleteObjectByRoleId(@Param("id") Integer id);

//    List<Map<String,Object>> findObjectsByRoleId(Integer roseId);
}
