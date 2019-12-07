package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysLogDao {

    public static final String TABLE_NAME = "sys_logs";

    int insertObject(SysLog sysLog);


    /**
     * @param ids
     * @return
     */
    int deleteObjects(@Param("ids") Integer... ids);


    /**
     * @return 返回日志条数
     */
    int getRowCount(String username);

    @Select("SELECT * FROM " + TABLE_NAME + " LIMIT #{start},#{end}")
    List<SysLog> findByPage(Integer start, Integer end);

    /**
     * 基于调价查询当前页呈现的数据
     *
     * @param username   查询条件
     * @param startIndex 起始位置
     * @param pageSize   页面大小(每页最多要显示多少条记录)
     * @return 当前页对应的日志记录
     */
    List<SysLog> findPageObjects(
            @Param("username") String username,
            @Param("startIndex") Integer startIndex,
            @Param("pageSize") Integer pageSize);

    @Select("SELECT * FROM " + TABLE_NAME)
    List<SysLog> findAll();





}
