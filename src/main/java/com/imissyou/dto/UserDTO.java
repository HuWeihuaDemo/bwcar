package com.imissyou.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String captcha;
    private boolean rememberMe;

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
