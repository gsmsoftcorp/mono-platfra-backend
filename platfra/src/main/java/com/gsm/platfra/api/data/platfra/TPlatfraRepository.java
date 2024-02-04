package com.gsm.platfra.api.data.platfra;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TPlatfraRepository extends JpaRepository<TPlatfra, Long> {
    TPlatfra findByPlatfraId(String platfraId);
}