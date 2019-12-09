package com.cy.pj.sys.dao;


import com.cy.pj.sys.entity.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysRoleDao {


    int insertObject(SysRole sysRole);

    /**
     * 删除角色
     *
     * @param id id
     * @return 删除结果
     */
    @Delete("delete from sys_roles where id = #{id}")
    int deleteRole(@Param("id") Integer id);

    /**
     * 基于条件(模糊查询)统计角色总数
     *
     * @param name 条件
     * @return 记录总数
     */
    int getRowCount(String name);

    @Select("select * from sys_roles where name = #{name}")
    SysRole getObjectByName(@Param("name") String name);

    /**
     * 基于条件(模糊)查询角色记录
     *
     * @param name       条件
     * @param startIndex 当前页
     * @param pageSize   每页大小
     * @return 当前页要呈现的记录
     */
    List<SysRole> findObjects(@Param("name") String name,
                              @Param("startIndex") Integer startIndex,
                              @Param("pageSize") Integer pageSize);

}
