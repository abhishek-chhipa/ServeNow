package com.abhishek.ServiceBookingSystem.services.client;

import com.abhishek.ServiceBookingSystem.dto.AdDTO;
import com.abhishek.ServiceBookingSystem.dto.AdDetailsForClientDTO;
import com.abhishek.ServiceBookingSystem.dto.ReservationDTO;
import com.abhishek.ServiceBookingSystem.dto.ReviewDTO;

import java.util.List;

public interface ClientService {
    List<AdDTO> getAllAds();

    List<AdDTO> searchAdByName(String name);

    boolean bookService(ReservationDTO reservationDTO);

    AdDetailsForClientDTO getAdDetailsByAdId(Long id);

    List<ReservationDTO> getAllBookingsByUserId(Long userId);

    Boolean giveReview(ReviewDTO reviewDTO);

}
