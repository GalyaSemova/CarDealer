package com.example.mobilele.model.entity;

import com.example.mobilele.model.enums.EnginEnum;
import com.example.mobilele.model.enums.TransmissionEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.UUID;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity{
    @NotNull
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;
    @NotEmpty
    private String description;
    @NotNull
    @ManyToOne
    private ModelEntity model;
    @NotNull
    @Enumerated(EnumType.STRING)
    private EnginEnum engine;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TransmissionEnum transmission;
    @NotEmpty
    private String imageUrl;
    @Positive
    private int mileage;
    @NotNull
    private BigDecimal price;
    @Min(1930)
    private int year;

    public OfferEntity() {
    }

    public OfferEntity(@NotNull UUID uuid, String description, @NotNull ModelEntity model, @NotNull EnginEnum engine, @NotNull TransmissionEnum transmission, String imageUrl, int mileage, @NotNull BigDecimal price, int year) {
        this.uuid = uuid;
        this.description = description;
        this.model = model;
        this.engine = engine;
        this.transmission = transmission;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.price = price;
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
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

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
