package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SysRoleMenuDao {

    /**
     * 基于菜单id删除角色和菜单关联数据
     * @param id
     */
    @Delete("delete from sys_role_menus where menu_id=#{id}")
    int deleteObjectsByMenuId(Integer id);
}
