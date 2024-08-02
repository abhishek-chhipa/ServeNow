package com.abhishek.ServiceBookingSystem.dto;

import lombok.Data;

@Data
public class SignupRequestDTO {
    private Long id;

    private String email;

    private String name;

    private String lastname;

    private String password;

    private String phone;
}
