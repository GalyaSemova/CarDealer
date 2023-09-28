package com.example.mobilele.model.entity;

import com.example.mobilele.model.enums.ModelCategory;
import jakarta.persistence.*;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity{

    private String name;
    @Enumerated(EnumType.STRING)
    private ModelCategory modelCategory;

    @ManyToOne
    private BrandEntity brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModelCategory getModelCategory() {
        return modelCategory;
    }

    public void setModelCategory(ModelCategory modelCategory) {
        this.modelCategory = modelCategory;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }
}
