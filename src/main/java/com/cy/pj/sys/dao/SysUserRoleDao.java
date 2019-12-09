package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SysUserRoleDao {

    @Delete("delete from sys_user_roles where role_id=#{id}")
    int deleteObjectsByRoleId(Integer id);

}
