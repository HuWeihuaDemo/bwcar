package com.imissyou.exception;

import lombok.Data;

@Data
public class RExeption extends RuntimeException {
    private int code;
    private String msg;
}
