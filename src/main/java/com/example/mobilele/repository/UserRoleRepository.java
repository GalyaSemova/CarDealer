package com.example.mobilele.repository;

import com.example.mobilele.model.entity.UserRoleEntity;
import com.example.mobilele.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    List<UserRoleEntity> findAllByRoleIn(List<UserRoleEnum> roles);
}
