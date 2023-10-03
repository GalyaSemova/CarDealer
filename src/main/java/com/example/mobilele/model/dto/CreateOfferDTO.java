package com.example.mobilele.model.dto;

import com.example.mobilele.model.enums.EnginEnum;
import com.example.mobilele.model.enums.TransmissionEnum;

public record CreateOfferDTO(
        String description,
        Long modelId,
        EnginEnum engine,
        TransmissionEnum transmission,
        String imageUrl,
        Integer mileage,
        Integer price,
        Integer year
) {
}
