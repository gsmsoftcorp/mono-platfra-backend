package com.gsm.platfra.api.data.feature.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TFeatureViewRepository extends JpaRepository<TFeatureView, Long> {
}
