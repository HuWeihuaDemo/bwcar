package com.imissyou.dto;

import lombok.Data;

@Data
public class QueryDTO {

    //http://localhost:8081/sys/menu/list?order=asc&limit=10&offset=0
    private String order;
    private int limit;
    private int offset;
    private String sort;  //排序的字段
    private String search;  //搜索
    private String parentName;

}
