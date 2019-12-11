package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysUserRoleDao {

    @Delete("delete from sys_user_roles where role_id=#{id}")
    void deleteObjectsByRoleId(Integer id);

    @Delete("delete from sys_user_roles where user_id=#{id}")
    void deleteObjectsByUserId(Integer id);

    /**
     * 写入用户角色关系数据
     *
     * @param userId 用户id
     * @param ids    角色id
     * @return 写入条数
     */
    int insertObjectS(@Param("userId") Integer userId, @Param("ids") List<Integer> ids);

    List<Integer> findObjectByUserId(Integer userId);
}
