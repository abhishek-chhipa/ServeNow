package com.abhishek.ServiceBookingSystem.dto;

import com.abhishek.ServiceBookingSystem.enums.ReservationStatus;
import com.abhishek.ServiceBookingSystem.enums.ReviewStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDTO {
    private Long id;

    private String serviceName;

    private ReservationStatus reservationStatus;

    private ReviewStatus reviewStatus;

    private Date bookDate;

    private String userName;

    private Long userId;

    private Long companyId;

    private Long adId;

}
