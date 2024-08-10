package com.abhishek.ServiceBookingSystem.services.company;

import com.abhishek.ServiceBookingSystem.dto.AdDTO;

import java.io.IOException;
import java.util.List;

public interface CompanyService {
    public Boolean postAd(Long userId, AdDTO adDTO) throws IOException;

    public List<AdDTO> getAllAds(Long userid);

    public AdDTO getAdById(Long userId);

    public Boolean updateAd(Long adId, AdDTO adDTO) throws IOException;

    public boolean deleteAd(Long adId);
}
