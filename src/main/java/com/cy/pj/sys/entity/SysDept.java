package com.cy.pj.sys.entity;

import ch.qos.logback.classic.db.names.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysDept extends AbstractObject {

    private static final long serialVersionUID = 8110315520287757419L;

    public static final String TABLE_NAME = "sys_depts";

    private String name;
    public static final String PARENT_ID = "parentId";
    private Integer parentId;
    private Integer sort;
    private String note;

}
