package com.gsm.platfra.api.services.feature.repository;

import com.gsm.platfra.api.entity.common.TCommonCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TCommonCodeRepository extends JpaRepository<TCommonCode, Long> {

}
