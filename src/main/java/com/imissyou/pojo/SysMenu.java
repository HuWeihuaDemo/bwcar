package com.imissyou.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysMenu implements Serializable {
    private Long menuId;

    private Long parentId;

    private String name;

    private String url;

    private String perms;

    private Integer type;

    private String icon;

    private Integer orderNum;

    private String parentName;

    private static final long serialVersionUID = 1L;


}