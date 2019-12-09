package com.cy.pj.sys.entity;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysMenu extends AbstractObject {

    private static final long serialVersionUID = 4333364133921124923L;

    @NotNull(message = "菜单名字不能为空")
    private String name;
    private String url;
    private Integer type ;
    private Integer sort;
    private String note;
    private Integer parentId;
    private String permission;



}
