package com.example.proje.core.entities.dtos.request;

import lombok.Data;

@Data
public class UserChangePasswordDto {

    private String email;

    private String password;
}
