package com.example.mobilele.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String brand;
//TODO: rename to 'name' and make it unique
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
