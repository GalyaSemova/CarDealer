package com.example.mobilele.model.entity;

import com.example.mobilele.model.enums.EnginEnum;
import com.example.mobilele.model.enums.TransmissionEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.UUID;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity{
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;
    private String description;
    @ManyToOne
    private ModelEntity model;
    @Enumerated(EnumType.STRING)
    private EnginEnum engine;
    @Enumerated(EnumType.STRING)
    private TransmissionEnum transmission;
    private String imageUrl;
    private long mileage;
    private BigDecimal price;
    private int year;

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

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
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
