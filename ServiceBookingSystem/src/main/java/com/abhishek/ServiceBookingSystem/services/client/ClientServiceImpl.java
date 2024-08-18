package com.abhishek.ServiceBookingSystem.services.client;

import com.abhishek.ServiceBookingSystem.dto.AdDTO;
import com.abhishek.ServiceBookingSystem.dto.AdDetailsForClientDTO;
import com.abhishek.ServiceBookingSystem.dto.ReservationDTO;
import com.abhishek.ServiceBookingSystem.dto.ReviewDTO;
import com.abhishek.ServiceBookingSystem.entity.Ad;
import com.abhishek.ServiceBookingSystem.entity.Reservation;
import com.abhishek.ServiceBookingSystem.entity.Review;
import com.abhishek.ServiceBookingSystem.entity.User;
import com.abhishek.ServiceBookingSystem.enums.ReservationStatus;
import com.abhishek.ServiceBookingSystem.enums.ReviewRepository;
import com.abhishek.ServiceBookingSystem.enums.ReviewStatus;
import com.abhishek.ServiceBookingSystem.repository.AdRepository;
import com.abhishek.ServiceBookingSystem.repository.ReservationRepository;
import com.abhishek.ServiceBookingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    private AdRepository adRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReviewRepository reviewRepository;


    public List<AdDTO> getAllAds(){
        return adRepository.findAll().stream().map(Ad::getAdDTO).collect(Collectors.toList());
    }

    public List<AdDTO> searchAdByName(String name){
        return adRepository.findAllByServiceNameContaining(name).stream().map(Ad::getAdDTO).collect(Collectors.toList());
    }

    public boolean bookService(ReservationDTO reservationDTO){
        Optional<Ad> optionalAd = adRepository.findById(reservationDTO.getAdId());
        Optional<User> optionalUser = userRepository.findById(reservationDTO.getUserId());

        if (optionalAd.isPresent() && optionalUser.isPresent()){
            Reservation reservation = new Reservation();
            reservation.setBookDate(reservationDTO.getBookDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);
            reservation.setUser(optionalUser.get());
            reservation.setAd(optionalAd.get());
            reservation.setCompany(optionalAd.get().getUser());
            reservation.setReviewStatus(ReviewStatus.FALSE);

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    public AdDetailsForClientDTO getAdDetailsByAdId(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);
        AdDetailsForClientDTO adDetailsDto = new AdDetailsForClientDTO();
        if(optionalAd.isPresent()){
            adDetailsDto.setAdDTO(optionalAd.get().getAdDTO());
            List<Review> reviewList = reviewRepository.findAllByAdId(adId);
            adDetailsDto.setReviewDTOList(reviewList.stream().map(Review::getDto).collect(Collectors.toList()));
        }
        return adDetailsDto;
    }

    @Override
    public List<ReservationDTO> getAllBookingsByUserId(Long userId) {
        return reservationRepository.findAllByUserId(userId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());

    }

    public Boolean giveReview(ReviewDTO reviewDTO){
        Optional<User> optionalUser  = userRepository.findById(reviewDTO.getUserId());
        Optional<Reservation> optionalReservation = reservationRepository.findById(reviewDTO.getBookId());

        if(optionalUser.isPresent() && optionalReservation.isPresent()){
            Review review = new Review();

            review.setReviewDate(new Date());
            review.setReview(reviewDTO.getReview());
            review.setRating(reviewDTO.getRating());

            review.setUser(optionalUser.get());
            review.setAd(optionalReservation.get().getAd());

            reviewRepository.save(review);

            Reservation reservation = optionalReservation.get();
            reservation.setReviewStatus(ReviewStatus.TRUE);

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

}
