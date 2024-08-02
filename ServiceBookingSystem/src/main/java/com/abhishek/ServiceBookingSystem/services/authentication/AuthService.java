package com.abhishek.ServiceBookingSystem.services.authentication;

import com.abhishek.ServiceBookingSystem.dto.SignupRequestDTO;
import com.abhishek.ServiceBookingSystem.dto.UserDto;

public interface AuthService {
    UserDto signUpClient(SignupRequestDTO signupRequestDTO);

    UserDto signUpCompany(SignupRequestDTO signupRequestDTO);

    Boolean presentByEmail(String email);
}
