package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface SysLogDao extends BaseDao<SysLog> {

    @Override
    @Insert("INSERT INTO sys_logs( username, operation, method, params, time, ip, createdTime) " +
            "values (#{username}, #{operation}, #{method}, #{params}, #{time}, #{ip}, #{createdTime})")
    int insertObject(SysLog sysLog);

}
