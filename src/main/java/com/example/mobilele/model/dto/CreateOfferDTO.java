package com.example.mobilele.model.dto;

import com.example.mobilele.model.enums.EnginEnum;
import com.example.mobilele.model.enums.TransmissionEnum;
import com.example.mobilele.model.validation.YearNotInTheFuture;
import jakarta.validation.constraints.*;

public record CreateOfferDTO(@NotEmpty @Size(min = 5, max = 512) String description,
                             @Positive @NotNull Long modelId, @NotNull EnginEnum engine,
                             @NotNull TransmissionEnum transmission, @NotEmpty String imageUrl,
                             @Positive @NotNull Integer mileage,
                             @Positive @NotNull Integer price,
                             @YearNotInTheFuture(message = "The year should not be in the future!")
                             @NotNull(message = "Year must be provided!")
                             @Min(1930)
                             Integer year) {

        public static CreateOfferDTO empty() {
                return new CreateOfferDTO(null, null, null, null, null, null, null, null);
        }
}

