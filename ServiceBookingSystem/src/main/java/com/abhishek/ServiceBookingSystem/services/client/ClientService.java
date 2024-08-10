package com.abhishek.ServiceBookingSystem.services.client;

import com.abhishek.ServiceBookingSystem.dto.AdDTO;

import java.util.List;

public interface ClientService {
    public List<AdDTO> getAllAds();

    List<AdDTO> searchAdByName(String name);
}
