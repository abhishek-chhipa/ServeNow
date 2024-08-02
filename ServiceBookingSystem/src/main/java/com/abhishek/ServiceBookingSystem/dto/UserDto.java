package com.abhishek.ServiceBookingSystem.dto;

import com.abhishek.ServiceBookingSystem.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String email;

    private String name;

    private String lastname;

    private String password;

    private String phone;

    private UserRole role;
}
