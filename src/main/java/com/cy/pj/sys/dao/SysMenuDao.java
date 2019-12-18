package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.pojo.UserMenuVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
@Component
public interface SysMenuDao extends BaseDao<SysMenu> {

    @Override
    @Insert("INSERT INTO sys_menus(id,name,url,type,sort,note,parentId,permission,createdTime,modifiedTime," +
            "createdUser,modifiedUser) " +
            "VALUES (null, #{name}, #{url}, #{type}, #{sort}, #{note}, #{parentId}, " +
            "#{permission}, now(), now(), #{createdUser}, #{modifiedUser})")
    int insertObject(SysMenu sysMenu);

    @Override
    int updateObject(SysMenu sysMenu);

    /**
     * 查询所有菜单以及对应的上级菜单名称
     * 一行记录映射为一个map对象(key为表中字段名,value为每行记录所对应的是列)
     *
     * @return list<map>
     */
    @Select("SELECT c.id,c.name,c.parentId,c.type,c.sort,c.url,c.permission, p.name parentName FROM sys_menus c  " +
            "LEFT JOIN sys_menus p on c.parentId = p.id")
    List<Map<String, Object>> findMapObjects();

    @Select("SELECT id,name,parentId,url,type FROM sys_menus")
    List<Map<String, Object>> findZTreeMap();

//    List<Map<String, Object>> findMenusByUserId(Integer userId);

    List<String> findPermissionByMenuIds(@Param("ids") Integer[] ids);

//    List<Map<String, Object>> findZTreeMapByUserId2(Integer userId);

    List<UserMenuVo> findMenusByUserId(@Param("userId") Integer userId, @Param("lay") Boolean lay );

    Set<UserMenuVo> findZTreeMapByUserId(@Param("userId") Integer userId );
}
