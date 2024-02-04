package com.gsm.platfra.api.services.features.like.repository;

import com.gsm.platfra.api.data.feature.like.TFeatureLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TFeatureLikeRepository extends JpaRepository<TFeatureLike, Long> {
}
