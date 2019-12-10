package com.cy.pj.sys.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser extends AbstractObject {

    private static final long serialVersionUID = -953112109932955733L;

    private String username;
    private String password;
    private String salt;//盐值  用于加密密码
    private String email;
    private String mobile;
    private Character valid=1;
    private Integer deptId;
    private SysDept sysDept;
    private Integer[] roleIds;


}
