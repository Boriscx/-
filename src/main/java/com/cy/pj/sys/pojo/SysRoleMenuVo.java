package com.cy.pj.sys.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysRoleMenuVo implements Serializable {

    private Integer id;
    private String name;
    private String note;
    private List<Integer> menuIds;

}
