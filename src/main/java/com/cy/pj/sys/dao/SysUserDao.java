package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface SysUserDao {

    int insertObject(SysUser sysUser);

    int updateObject(SysUser sysUser);

    @Update("UPDATE sys_users SET valid=#{valid} WHERE id=#{id}")
    int updateValidById(@Param("id") Integer id, @Param("valid") Integer valid);

    @Select("SELECT * FROM sys_users WHERE id= #{id}")
    SysUser getById(Integer id);

    @Select("SELECT * FROM sys_users WHERE username = #{username}")
    SysUser getByName(String username);

    @Select("SELECT COUNT(*) FROM sys_users WHERE deptId=#{deptId}")
    int findObjectByDeptId(Integer deptId);

    int getRowCount(String username);

    List<SysUser> findObjects(@Param("username") String username,
                              @Param("currentIndex") Integer currentIndex,
                              @Param("pageSize") Integer pageSize);

    SysUser findObjectById(Integer id);
}
