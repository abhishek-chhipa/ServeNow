package com.abhishek.ServiceBookingSystem.repository;

import com.abhishek.ServiceBookingSystem.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
}
