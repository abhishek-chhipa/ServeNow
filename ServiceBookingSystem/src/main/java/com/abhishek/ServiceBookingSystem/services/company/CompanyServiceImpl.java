package com.abhishek.ServiceBookingSystem.services.company;

import com.abhishek.ServiceBookingSystem.dto.AdDTO;
import com.abhishek.ServiceBookingSystem.dto.ReservationDTO;
import com.abhishek.ServiceBookingSystem.entity.Ad;
import com.abhishek.ServiceBookingSystem.entity.Reservation;
import com.abhishek.ServiceBookingSystem.entity.User;
import com.abhishek.ServiceBookingSystem.enums.ReservationStatus;
import com.abhishek.ServiceBookingSystem.repository.AdRepository;
import com.abhishek.ServiceBookingSystem.repository.ReservationRepository;
import com.abhishek.ServiceBookingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public Boolean postAd(Long userId, AdDTO adDTO) throws IOException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Ad ad = new Ad();
            ad.setServiceName(adDTO.getServiceName());
            ad.setDescription(adDTO.getDescription());
            ad.setImage(adDTO.getImg().getBytes());
            ad.setPrice(adDTO.getPrice());
            ad.setUser(optionalUser.get());

            adRepository.save(ad);
            return true;
        }
        return false;
    }

    public List<AdDTO> getAllAds(Long userid) {
        return adRepository.findAllByUserId(userid).stream().map(Ad::getAdDTO).collect(Collectors.toList());
    }

    public AdDTO getAdById(Long adId) {
        Optional<Ad> optionalAd = adRepository.findById(adId);
        return optionalAd.map(Ad::getAdDTO).orElse(null);
    }

    public Boolean updateAd(Long adId, AdDTO adDTO) throws IOException {
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if (optionalAd.isPresent()) {
            Ad ad = optionalAd.get();

            ad.setServiceName(adDTO.getServiceName());
            ad.setDescription(adDTO.getDescription());
            if (adDTO.getImg() != null) {
                ad.setImage(adDTO.getImg().getBytes());
            }
            ad.setPrice(adDTO.getPrice());

            adRepository.save(ad);
            return true;

        }
        return false;
    }

    public boolean deleteAd(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if (optionalAd.isPresent()){
            adRepository.delete(optionalAd.get());
            return true;
        }
        return false;
    }

    public List<ReservationDTO> getAllAdBookings(Long companyId){
        return reservationRepository.findAllByCompanyId(companyId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    public Boolean changeBookingStatus(Long bookingId, String status)
    {
        Optional<Reservation> optionalReservation = reservationRepository.findById(bookingId);
        if(optionalReservation.isPresent()){
            Reservation existingReservation = optionalReservation.get();
            if(Objects.equals(status,"Approve")){
                existingReservation.setReservationStatus(ReservationStatus.APPROVED);

            }else {
                existingReservation.setReservationStatus(ReservationStatus.REJECTED);
            }

            reservationRepository.save(existingReservation);
            return true;
        }
        return false;
    }
}
