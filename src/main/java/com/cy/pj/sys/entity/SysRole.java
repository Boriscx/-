package com.cy.pj.sys.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysRole extends AbstractObject {

    private static final long serialVersionUID = 3107729701135644440L;

    private String name;
    private String note;
    private Integer[] menuIds;

}

