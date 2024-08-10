package com.abhishek.ServiceBookingSystem.controller;

import com.abhishek.ServiceBookingSystem.dto.AdDTO;
import com.abhishek.ServiceBookingSystem.services.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/ad/{userId}")
    public ResponseEntity<?> postAd(@PathVariable Long userId, @ModelAttribute AdDTO adDTO) throws IOException {
        boolean success = companyService.postAd(userId,adDTO);

        if (success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/ads/{userId}")
    public ResponseEntity<?> getAllAdsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(companyService.getAllAds(userId));
    }

    @GetMapping("/ad/{adId}")
    public ResponseEntity<?> getAdById(@PathVariable Long adId){
        AdDTO adDTO= companyService.getAdById(adId);
        if(!Objects.isNull(adDTO))  return ResponseEntity.ok(adDTO);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/ad/{adId}")
    public ResponseEntity<?> updateAd(@PathVariable Long adId, @ModelAttribute AdDTO adDTO) throws IOException{
        boolean success = companyService.updateAd(adId,adDTO);
        if (success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/ad/{adId}")
    public ResponseEntity<?> deleteAd(@PathVariable Long adId) throws IOException{
        boolean success = companyService.deleteAd(adId);
        if (success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }



}
