package com.example.mobilele.service.impl;

import com.example.mobilele.model.dto.CreateOfferDTO;
import com.example.mobilele.model.entity.ModelEntity;
import com.example.mobilele.model.entity.OfferEntity;
import com.example.mobilele.repository.ModelRepository;
import com.example.mobilele.repository.OfferRepository;
import com.example.mobilele.service.OfferService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;

    public OfferServiceImpl(OfferRepository offerRepository, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public UUID createOffer(CreateOfferDTO createOfferDTO) {
        OfferEntity newOffer = map(createOfferDTO);
        ModelEntity modelEntity = modelRepository.findById(createOfferDTO.getModelId())
                .orElseThrow(() -> new IllegalArgumentException("Model with id " + createOfferDTO.getModelId() +
                        " not found"));
        newOffer.setModel(modelEntity);

        newOffer = offerRepository.save(newOffer);

        return newOffer.getUuid();
    }

    private OfferEntity map(CreateOfferDTO createOfferDTO) {
        OfferEntity offer = new OfferEntity();
        offer.setUuid(UUID.randomUUID());
        offer.setDescription(createOfferDTO.getDescription());
        offer.setEngine(createOfferDTO.getEngine());
        offer.setMileage(createOfferDTO.getMileage());
        offer.setTransmission(createOfferDTO.getTransmission());
        offer.setImageUrl(createOfferDTO.getImageUrl());
        offer.setPrice(BigDecimal.valueOf(createOfferDTO.getPrice()));
        offer.setYear(createOfferDTO.getYear());

        return offer;
    }
}
