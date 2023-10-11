package com.example.mobilele.model.dto;

import com.example.mobilele.model.enums.EnginEnum;
import com.example.mobilele.model.enums.TransmissionEnum;
import com.example.mobilele.model.validation.YearNotInTheFuture;
import jakarta.validation.constraints.*;

public class CreateOfferDTO {

        @NotEmpty
        @Size(min = 5, max = 512)
        private String description;
        @Positive
        @NotNull
        private Long modelId;
        @NotNull
        private EnginEnum engine;
        @NotNull
        private TransmissionEnum transmission;
        @NotEmpty
        private String imageUrl;
        @Positive
        @NotNull
        private Integer mileage;
        @Positive
        @NotNull
        private Integer price;
        @YearNotInTheFuture(message = "The year should not be in the future")
        @NotNull(message = "Year must be provided")
        @Min(1930)
        private Integer year;

//        public CreateOfferDTO (){
//                this(null,null,null,null, null,null,null,null);
//        }


        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public Long getModelId() {
                return modelId;
        }

        public void setModelId(Long modelId) {
                this.modelId = modelId;
        }

        public EnginEnum getEngine() {
                return engine;
        }

        public void setEngine(EnginEnum engine) {
                this.engine = engine;
        }

        public TransmissionEnum getTransmission() {
                return transmission;
        }

        public void setTransmission(TransmissionEnum transmission) {
                this.transmission = transmission;
        }

        public String getImageUrl() {
                return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
        }

        public Integer getMileage() {
                return mileage;
        }

        public void setMileage(Integer mileage) {
                this.mileage = mileage;
        }

        public Integer getPrice() {
                return price;
        }

        public void setPrice(Integer price) {
                this.price = price;
        }

        public Integer getYear() {
                return year;
        }

        public void setYear(Integer year) {
                this.year = year;
        }
}
