package com.cy.pj.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysDept implements Serializable {

    private static final long serialVersionUID = 8110315520287757419L;

    private Integer id;
    private String name;
    private Integer parentId;
    private Integer sort;
    private String note;

    protected String createdUser;
    protected String modifiedUser;

    protected Date createdTime;
    protected Date modifiedTime;
}
