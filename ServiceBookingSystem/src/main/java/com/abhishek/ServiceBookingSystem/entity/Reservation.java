package com.abhishek.ServiceBookingSystem.entity;

import com.abhishek.ServiceBookingSystem.dto.ReservationDTO;
import com.abhishek.ServiceBookingSystem.enums.ReservationStatus;
import com.abhishek.ServiceBookingSystem.enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ReservationStatus reservationStatus;

    private ReviewStatus reviewStatus;

    private Date bookDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ad_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ad ad;


    public ReservationDTO getReservationDto(){

        ReservationDTO dto = new ReservationDTO();
        dto.setId(id);
        dto.setReservationStatus(reservationStatus);
        dto.setReviewStatus(reviewStatus);
        dto.setBookDate(bookDate);
        dto.setServiceName(ad.getServiceName());
        dto.setUserName(user.getName());
        dto.setUserId(user.getId());
        dto.setCompanyId(company.getId());
        dto.setAdId(ad.getId());
        return dto;
    }


}
