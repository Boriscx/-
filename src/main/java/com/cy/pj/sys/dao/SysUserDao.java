package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysUserDao extends BaseDao<SysUser> {

    @Override
    int insertObject(SysUser sysUser);

    @Override
    int updateObject(SysUser sysUser);

    @Update("UPDATE sys_users SET valid=#{valid} WHERE id=#{id}")
    int updateValidById(@Param("id") Integer id, @Param("valid") Integer valid);

//    int getRowCount(String key);

    SysUser findUserRoleById(Integer id);

    List<SysUser> findPageObjects(@Param("key") String key,
                            @Param("currentIndex") Integer currentIndex,
                            @Param("pageSize") Integer pageSize);
}
