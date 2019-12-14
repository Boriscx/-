package com.cy.pj.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysMenu extends AbstractObject {

    private static final long serialVersionUID = 4333364133921124923L;

    public static final String TABLE_NAME = "sys_menus";
    public static final String NAME = "name";

    @NotBlank(message = "菜单名字不能为空")
    private String name;
    private String url;
    private Integer type;
    private Integer sort;
    private String note;
    public static final String PARENT_ID = "parentID";
    private Integer parentId;
    private String permission;

    @JsonIgnore
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
}
