package com.cy.pj.sys.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser implements Serializable {

    private static final long serialVersionUID = -953112109932955733L;

    protected Integer id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private String mobile;
    private Character valid;
    protected String createdUser;
    protected String modifiedUser;
    protected Date createdTime;
    protected Date modifiedTime;
    private SysDept sysDept;

}
