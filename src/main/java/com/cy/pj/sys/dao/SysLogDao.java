package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface SysLogDao extends BaseDao<SysLog> {

    /**
     * 添加对象
     *
     * @param sysLog 对象信息
     * @return 添加结果
     */
    int insertObject(SysLog sysLog);

    List<SysLog> findPageObjects(@Param("key") String key,
                            @Param("currentIndex") Integer currentIndex,
                            @Param("pageSize") Integer pageSize);

}
