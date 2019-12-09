package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SysUserRoleDao {

    @Delete("delete from sys_user_roles where role_id=#{id}")
    void deleteObjectsByRoleId(Integer id);

    @Delete("delete from sys_user_roles where user_id=#{id}")
    void deleteObjectsByUserId(Integer id);

    int insertObjectS(@Param("userId") Integer userId, @Param("ids") Integer[] ids);

}
