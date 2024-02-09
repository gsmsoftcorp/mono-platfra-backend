package com.gsm.platfra.api.data.platfra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TPlatfraRepository extends JpaRepository<TPlatfra, Long> {
    TPlatfra findByPlatfraId(String platfraId);

    // Todo : Optional 정책을 사용하도록 팀 회의
    Optional<TPlatfra> findTPlatfraByPlatfraId(String platfraId);
}