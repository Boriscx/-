package com.cy.pj.sys.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Node implements Serializable {

    private static final long serialVersionUID = -5914051282076951379L;

    private Integer id;
    private String name;
    private Integer parentId;
    private String url;
    private Integer type;
}
