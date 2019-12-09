package com.cy.pj.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractObject implements Serializable {

    private static final long serialVersionUID = -9112685812872843147L;
    protected Integer id;

    protected String createdUser;
    protected String modifiedUser;

    protected Date createdTime;
    protected Date modifiedTime;

}
