package com.abhishek.ServiceBookingSystem.services.company;

import com.abhishek.ServiceBookingSystem.repository.AdRepository;
import com.abhishek.ServiceBookingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;


}
