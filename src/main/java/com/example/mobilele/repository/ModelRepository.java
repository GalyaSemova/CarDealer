package com.example.mobilele.repository;

import com.example.mobilele.model.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
    Arrays findAllByBrandId(Long id);
}
