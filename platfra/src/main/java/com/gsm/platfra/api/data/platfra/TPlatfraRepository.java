package com.gsm.platfra.api.data.platfra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TPlatfraRepository extends JpaRepository<TPlatfra, Long> {
    Optional<TPlatfra> findByPlatfraId(String platfraId);
    Optional<TPlatfra> findTPlatfraByPlatfraId(String platfraId);

    List<TPlatfra> findAllByOwnerId(String userId);
}