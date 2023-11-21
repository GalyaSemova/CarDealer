package com.example.mobilele.service.impl;

import com.example.mobilele.model.dto.CreateOfferDTO;
import com.example.mobilele.model.dto.OfferDetailDTO;
import com.example.mobilele.model.dto.OfferSummaryDTO;
import com.example.mobilele.model.entity.ModelEntity;
import com.example.mobilele.model.entity.OfferEntity;
import com.example.mobilele.model.entity.UserEntity;
import com.example.mobilele.model.enums.UserRoleEnum;
import com.example.mobilele.repository.ModelRepository;
import com.example.mobilele.repository.OfferRepository;
import com.example.mobilele.repository.UserRepository;
import com.example.mobilele.service.OfferService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;

    public OfferServiceImpl(OfferRepository offerRepository, ModelRepository modelRepository, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UUID createOffer(CreateOfferDTO createOfferDTO, UserDetails seller) {
        OfferEntity newOffer = map(createOfferDTO);
        ModelEntity modelEntity = modelRepository.findById(createOfferDTO.modelId())
                .orElseThrow(() -> new IllegalArgumentException("Model with id " + createOfferDTO.modelId() +
                        " not found"));

        UserEntity sellerEntity = userRepository.findByEmail(seller.getUsername())
                .orElseThrow(()->
                        new IllegalArgumentException("User with email " + seller.getUsername() + " was not found!"));

        newOffer.setModel(modelEntity);
        newOffer.setSeller(sellerEntity);

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
    public Optional<OfferDetailDTO> getOfferDetail(UUID offerUUID, UserDetails viewer) {
        return offerRepository.findByUuid(offerUUID)
                .map(o -> this.mapAsDetails(o, viewer));
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
                offerEntity.getImageUrl());
    }

    private OfferDetailDTO mapAsDetails(OfferEntity offerEntity, UserDetails viewer) {

        return new OfferDetailDTO(
                offerEntity.getUuid().toString(),
                offerEntity.getModel().getBrand().getBrand(),
                offerEntity.getModel().getName(),
                offerEntity.getYear(),
                offerEntity.getMileage(),
                offerEntity.getPrice(),
                offerEntity.getEngine(),
                offerEntity.getTransmission(),
                offerEntity.getImageUrl(),
                offerEntity.getSeller().getFirstName(),
                isOwner(offerEntity, viewer)
        );
    }

    private boolean isOwner(OfferEntity offerEntity, UserDetails viewer) {
        if (viewer == null) {
//            anonymous users own no offers
            return  false;
        }
        UserEntity viewerEntity =
                userRepository.findByEmail(viewer.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Unknown user..."));

        if (isAdmin(viewerEntity)) {
//            all admins own all offers
            return true;
        }
        return Objects
                .equals(offerEntity.getSeller().getId(),
                        viewerEntity.getId());
    }
    private boolean isAdmin(UserEntity userEntity) {
        return userEntity.getRoles()
                .stream()
                .map(r -> r.getRole())
                .anyMatch(r -> UserRoleEnum.ADMIN == r);
    }

    private OfferEntity map(CreateOfferDTO createOfferDTO) {
        OfferEntity offer = new OfferEntity();
        offer.setUuid(UUID.randomUUID());
        offer.setDescription(createOfferDTO.description());
        offer.setEngine(createOfferDTO.engine());
        offer.setMileage(createOfferDTO.mileage());
        offer.setTransmission(createOfferDTO.transmission());
        offer.setImageUrl(createOfferDTO.imageUrl());
        offer.setPrice(BigDecimal.valueOf(createOfferDTO.price()));
        offer.setYear(createOfferDTO.year());

        return offer;
    }
}
