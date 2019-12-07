package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.pojo.Node;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface SysMenuDao {

    @Insert(" INSERT INTO sys_menus VALUES (null, #{name}, #{url}, #{type}, #{sort}, #{note}, " +
            "#{parentId}, #{permission}, now(), now(), #{createdUser},#{modifiedUser})")
    int insertObject(SysMenu sysMenu);


    int updateObject(SysMenu sysMenu);

    /**
     * 基于id删除当前菜单
     *
     * @param id 当前菜单id
     * @return 删除数量
     */
    @Delete("DELETE FROM sys_menus WHERE id=#{id}")
    int deleteObject(Integer id);

    /**
     * 统计当前菜单中的子菜单数量
     *
     * @param id 当前菜单
     * @return 子菜单数量
     */
    @Select("SELECT COUNT(*) FROM sys_menus WHERE parentId=#{id}")
    int getChildCount(Integer id);

    /**
     * 查询所有菜单以及对应的上级菜单名称
     * 一行记录映射为一个map对象(key为表中字段名,value为每行记录所对应的是列)
     *
     * @return list<map>
     */
    @Select("SELECT * FROM sys_menus")
    List<Map<String, Object>> findObjects();

    //    @ResultMap("node_map")
    @Select("SELECT id,name,parentId,url,type FROM sys_menus")
    List<Node> findZtreeMenuNodes();

}
