package com.abhishek.ServiceBookingSystem.services.authentication;

import com.abhishek.ServiceBookingSystem.dto.SignupRequestDTO;
import com.abhishek.ServiceBookingSystem.dto.UserDto;
import com.abhishek.ServiceBookingSystem.entity.User;
import com.abhishek.ServiceBookingSystem.enums.UserRole;
import com.abhishek.ServiceBookingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDto signUpClient(SignupRequestDTO signupRequestDTO){
        User user = new User();
        user.setName(signupRequestDTO.getName());
        user.setLastname(signupRequestDTO.getLastname());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhone(signupRequestDTO.getPhone());
        user.setPassword(signupRequestDTO.getPassword());

        user.setRole(UserRole.CLIENT);
        return userRepository.save(user).getDto();
    }

    @Override
    public UserDto signUpCompany(SignupRequestDTO signupRequestDTO){
        User user = new User();
        user.setName(signupRequestDTO.getName());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhone(signupRequestDTO.getPhone());
        user.setPassword(signupRequestDTO.getPassword());

        user.setRole(UserRole.COMPANY);
        return userRepository.save(user).getDto();
    }

    @Override
    public Boolean presentByEmail(String email){
        return userRepository.findFirstByEmail(email) != null;
    }
}
