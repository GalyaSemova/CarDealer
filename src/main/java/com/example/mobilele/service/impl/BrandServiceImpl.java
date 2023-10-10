package com.example.mobilele.service.impl;

import com.example.mobilele.model.dto.BrandDTO;
import com.example.mobilele.model.dto.ModelDTO;
import com.example.mobilele.model.entity.ModelEntity;
import com.example.mobilele.repository.BrandRepository;
import com.example.mobilele.repository.ModelRepository;
import com.example.mobilele.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    public BrandServiceImpl(ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandDTO> getAllBrands() {

//        TODO: better implementation, sorting by brand name

        Map<String, BrandDTO> brands = new HashMap<>();

        for (ModelEntity model : modelRepository.findAll()) {
            if (!brands.containsKey(model.getBrand().getBrand())) {
                brands.put(model.getBrand().getBrand(),
                        new BrandDTO(model.getBrand().getBrand(),
                                new ArrayList<>()));
            }

            brands.get(model.getBrand().getBrand()).models().add(
                    new ModelDTO(model.getId(), model.getName()));

        }
        return brands.values().stream().toList();

//        return brandRepository.findAll().stream()
//                .map(brand->new BrandDTO(
//                        brand.getBrand(),
//                        modelRepository.findAllByBrandId(brand.getId()).stream()
//                                .map(model->new ModelDTO(model.getId(), model.getName()))
//                                .sorted(Comparator.comparing(ModelDTO::name))
//                                .collect(Collectors.toList())
//                ))
//                .sorted(Comparator.comparing(BrandDTO::name))
//                .collect(Collectors.toList());
    }
}
