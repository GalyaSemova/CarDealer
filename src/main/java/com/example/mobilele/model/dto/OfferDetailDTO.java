package com.example.mobilele.model.dto;

import com.example.mobilele.model.enums.EnginEnum;
import com.example.mobilele.model.enums.TransmissionEnum;

import java.math.BigDecimal;

public record OfferDetailDTO(
        String id,
        String brand,
        String model,
        int year,
        int mileage,
        BigDecimal price,
        EnginEnum engine,
        TransmissionEnum transmission,
        String imageUrl,
        String seller,
        boolean viewerIsOwner
) {
    public String summary() {
        return brand + " " + model + ", " + year;
    }
}
