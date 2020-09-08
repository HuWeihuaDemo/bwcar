package com.imissyou.dto;

import lombok.Data;

import java.util.List;

@Data
public class DataGridResult {

    private long total;
    private List<?> rows;

    DataGridResult(){}

    public DataGridResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }


}
