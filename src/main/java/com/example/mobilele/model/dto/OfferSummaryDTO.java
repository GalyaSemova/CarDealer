package com.example.mobilele.model.dto;

import com.example.mobilele.model.enums.EnginEnum;
import com.example.mobilele.model.enums.TransmissionEnum;

import java.math.BigDecimal;

public record OfferSummaryDTO(
        String id,
        String brand,
        String model,
        int year,
        int mileage,
        BigDecimal price,
        EnginEnum engin,
        TransmissionEnum transmission,
        String imageUrl

){
    public String summary() {
        return
                brand + " " + model + ", " + (year);
    }
}
