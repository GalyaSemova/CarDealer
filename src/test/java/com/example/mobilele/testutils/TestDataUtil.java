package com.example.mobilele.testutils;

import com.example.mobilele.model.entity.BrandEntity;
import com.example.mobilele.model.entity.ModelEntity;
import com.example.mobilele.model.entity.OfferEntity;
import com.example.mobilele.model.entity.UserEntity;
import com.example.mobilele.model.enums.EnginEnum;
import com.example.mobilele.model.enums.TransmissionEnum;
import com.example.mobilele.repository.BrandRepository;
import com.example.mobilele.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class TestDataUtil {
    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private BrandRepository brandRepository;

    public OfferEntity createTestOffer(UserEntity owner) {

        ModelEntity model1 = new ModelEntity();
        model1.setName("Test Model");
        ModelEntity model2 = new ModelEntity();
        model2.setName("Test Model1");
//        create test brand
        BrandEntity brand = new BrandEntity();
        brand.setBrand("Test Brand");
        brand.setModels(List.of(model1,model2));
        brandRepository.save(brand);

        OfferEntity offer = new OfferEntity();
        offer.setModel(brand.getModels().get(0));
        offer.setImageUrl("https://www.google.com");
        offer.setPrice(BigDecimal.valueOf(1000));
        offer.setYear(2020);
        offer.setUuid(UUID.randomUUID());
        offer.setDescription("Test Description");
        offer.setEngine(EnginEnum.PETROL);
        offer.setMileage(10000);
        offer.setTransmission(TransmissionEnum.MANUAL);
        offer.setSeller(owner);

        return offerRepository.save(offer);

    }

    public void cleanAllTestData() {
        offerRepository.deleteAll();
        brandRepository.deleteAll();
    }
}
