package com.cy.pj.sys.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserMenuVo implements Serializable {

    private static final long serialVersionUID = -5068593423136460356L;

    private Integer id;
    private String name;
    private String url;
    private Integer parentId;
    private Integer type;

    private List<UserMenuVo> childMenus;

}
