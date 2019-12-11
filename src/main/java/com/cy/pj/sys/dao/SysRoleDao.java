package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface SysRoleDao extends BaseDao<SysRole> {

    @Override
    int insertObject(SysRole sysRole);

    @Override
    int updateObject(SysRole sysRole);

    /**
     * 通过id查找角色
     * @param id id
     * @return 角色信息
     */
    SysRole getMapById(Integer id);

    List<Map<String,Object>> findAllObjects();

    List<SysRole> findPageObjects(@Param("key") String key,
                            @Param("currentIndex") Integer currentIndex,
                            @Param("pageSize") Integer pageSize);
}
