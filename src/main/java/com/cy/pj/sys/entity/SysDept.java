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
public class SysDept extends AbstractObject {

    private static final long serialVersionUID = 8110315520287757419L;

    private String name;
    private Integer parentId;
    private Integer sort;
    private String note;

}
