package com.cy.pj.sys.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysRole implements Serializable {

    private static final long serialVersionUID = 3107729701135644440L;

    protected Integer id;

    protected String createdUser;
    protected String modifiedUser;

    protected Date createdTime;
    protected Date modifiedTime;

    private String name;
    private String note;
}
