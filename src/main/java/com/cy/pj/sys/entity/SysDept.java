package com.cy.pj.sys.entity;

import ch.qos.logback.classic.db.names.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysDept extends AbstractObject {

    private static final long serialVersionUID = 8110315520287757419L;

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    public static final String TABLE_NAME = "sys_depts";

    @NotNull(message = "部门名称不能为空")
    private String name;
    public static final String PARENT_ID = "parentId";
    private Integer parentId;
    private String parentName;
    private Integer sort;
    private String note;

}
