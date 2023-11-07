package com.example.mobilele.service.impl;

import com.example.mobilele.model.dto.CreateOfferDTO;
import com.example.mobilele.model.dto.OfferDetailDTO;
import com.example.mobilele.model.dto.OfferSummaryDTO;
import com.example.mobilele.model.entity.ModelEntity;
import com.example.mobilele.model.entity.OfferEntity;
import com.example.mobilele.repository.ModelRepository;
import com.example.mobilele.repository.OfferRepository;
import com.example.mobilele.service.OfferService;
import com.example.mobilele.service.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
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

    @Override
    public Page<OfferSummaryDTO> getAllOffers(Pageable pageable) {
        return offerRepository
                .findAll(pageable)
                .map(OfferServiceImpl::mapAsSummary);
    }

    @Override
    public Optional<OfferDetailDTO> getOfferDetail(UUID offerUUID) {
        return offerRepository.findByUuid(offerUUID)
                .map(OfferServiceImpl::mapAsDetails);
    }

    @Override
    @Transactional
    public void deleteOffer(UUID offerUUID) {
        offerRepository.deleteByUuid(offerUUID);

    }

    private static OfferSummaryDTO mapAsSummary(OfferEntity offerEntity) {
        return new OfferSummaryDTO(
                offerEntity.getUuid().toString(),
                offerEntity.getModel().getBrand().getBrand(),
                offerEntity.getModel().getName(),
                offerEntity.getYear(),
                offerEntity.getMileage(),
                offerEntity.getPrice(),
                offerEntity.getEngine(),
                offerEntity.getTransmission(),
                offerEntity.getImageUrl()
        );

    }

    private static OfferDetailDTO mapAsDetails(OfferEntity offerEntity) {
//        TODO: reuse
        return new OfferDetailDTO(
                offerEntity.getUuid().toString(),
                offerEntity.getModel().getBrand().getBrand(),
                offerEntity.getModel().getName(),
                offerEntity.getYear(),
                offerEntity.getMileage(),
                offerEntity.getPrice(),
                offerEntity.getEngine(),
                offerEntity.getTransmission(),
                offerEntity.getImageUrl()
        );

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
