package com.example.proje.model.request.user;

import lombok.Data;

@Data

public class UserUpdateDto {

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;
}
