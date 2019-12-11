package com.cy.pj.sys.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysRole extends AbstractObject {

    private static final long serialVersionUID = 3107729701135644440L;

    public static final String TABLE_NAME = "sys_roles";

    public static final String NAME = "name";
    private String name;
    private String note;
    private List<Integer> menuIds;


    @Override
    public String getTableName() {
        return null;
    }
}

