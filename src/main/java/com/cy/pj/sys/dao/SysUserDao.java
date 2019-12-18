package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysUserDao extends BaseDao<SysUser> {

    @Update("UPDATE sys_users SET valid=#{valid} WHERE id=#{id}")
    int updateValidById(@Param("id") Integer id, @Param("valid") Integer valid);

    SysUser findUserRoleById(Integer id);


    @Update("update sys_users set password=#{password},salt=#{salt},modifiedTime=now() where id=#{id}")
    int updatePassword(@Param("password") String password, @Param("salt") String salt, @Param("id") Integer id);


    List<String> findPermissionByUserId(Integer userId);

}
