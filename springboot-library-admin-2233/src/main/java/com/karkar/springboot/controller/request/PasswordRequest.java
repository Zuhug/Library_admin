package com.karkar.springboot.controller.request;

import lombok.Data;

@Data
public class PasswordRequest {
    private String newPass;
    private String username;
    private String password;
}
