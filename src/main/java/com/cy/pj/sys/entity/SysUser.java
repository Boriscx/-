package com.cy.pj.sys.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser extends AbstractObject {

    private static final long serialVersionUID = -953112109932955733L;

    private String username;
    private String password;
    private String salt;
    private String email;
    private String mobile;
    private Character valid;

    private SysDept sysDept;

}
