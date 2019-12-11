package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.AbstractObject;
import com.cy.pj.sys.pojo.Node;
import com.cy.pj.sys.pojo.PageObject;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 包含了
 *  <p>getObjectById(tableName,id)</p>
 *  <p>getObjectByColumn(tableName,columnName,columnValue)</p>
 *  <p>findObjects()</p>
 *  <p>getRowCount(tableName,columnName,columnValue)</p>
 *  <p>deleteObjectById(id)</p>
 *  <p>deleteObjects(tableName,ids)</p>
 * @param <T> 实体泛型
 */
public interface BaseDao<T extends AbstractObject> {

    @Delete("DELETE FROM ${tableName} WHERE id=#{id}")
    int deleteObjectById(@Param("tableName") String tableName, @Param("id") Integer id);

    @Select("SELECT * FROM ${tableName} WHERE id=#{id}")
    T getObjectById(@Param("tableName") String tableName, @Param("id") Integer id);

    @Select("SELECT * FROM ${tableName} WHERE ${columnName}=#{columnValue}")
    T getObjectByColumn(String tableName,String columnName,Object columnValue);

    @Select("SELECT * FROM ${tableName}")
    List<T> findObjects(String tableName);

    int getRowCount(@Param("tableName") String tableName,
                    @Param("columnName") String columnName,
                    @Param("columnValue") Object columnValue);

    /**
     * 通过id删除对象
     * 可变参数,多个id
     *
     * @param tableName 表名
     * @param ids       ids
     * @return 删除结果
     */
    int deleteObjects(@Param("tableName") String tableName, @Param("ids") Integer... ids);

    int insertObject(T t);

    int updateObject(T t);

    List<T> findPageObjects(@Param("key") Object key,
                            @Param("startIndex") Integer startIndex,
                            @Param("pageSize") Integer pageSize);

    List<Map<String, Object>> findMapObjects();

    List<Map<String,Object>> findNodeObjects();

    List<Map<String,Object>> findZTreeMap();
}
