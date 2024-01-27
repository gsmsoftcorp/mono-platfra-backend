package com.gsm.platfra.api.services.platfra.repository;

import com.gsm.platfra.api.entity.platfra.TPlatfra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TPlatfraRepository extends JpaRepository<TPlatfra, Long> {
  TPlatfra findByPlatfraId(String platfraId);
}