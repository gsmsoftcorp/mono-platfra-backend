package com.gsm.platfra.api.services.feature.view.repository;

import com.gsm.platfra.api.entity.feature.TFeatureView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TFeatureViewRepository extends JpaRepository<TFeatureView, Long> {
}
