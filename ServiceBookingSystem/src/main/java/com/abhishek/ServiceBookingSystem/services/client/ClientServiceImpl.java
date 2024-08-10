package com.abhishek.ServiceBookingSystem.services.client;

import com.abhishek.ServiceBookingSystem.dto.AdDTO;
import com.abhishek.ServiceBookingSystem.entity.Ad;
import com.abhishek.ServiceBookingSystem.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    AdRepository adRepository;

    public List<AdDTO> getAllAds(){
        return adRepository.findAll().stream().map(Ad::getAdDTO).collect(Collectors.toList());
    }

    public List<AdDTO> searchAdByName(String name){
        return adRepository.findAllByServiceNameContaining(name).stream().map(Ad::getAdDTO).collect(Collectors.toList());
    }
}
