package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface SysRoleDao extends BaseDao<SysRole> {

    @Override
    @Insert("insert into sys_roles(name, note, createdTime, modifiedTime, createdUser, modifiedUser) " +
            "values (#{name}, #{note}, now(), now(), #{createdUser}, #{modifiedUser})")
    int insertObject(SysRole sysRole);

    /**
     * 通过id查找角色
     * @param id id
     * @return 角色信息
     */
    @ResultMap("sysRoleMenuVo")
    @Select("select r.id, r.name, r.note, srm.menu_id\n" +
            "        from sys_roles r\n" +
            "                 left join sys_role_menus srm on r.id = srm.role_id\n" +
            "        where r.id = #{id}")
    SysRole getMapById(Integer id);

    @Select("SELECT id, name from sys_roles")
    List<Map<String,Object>> findMapObjects();

}
