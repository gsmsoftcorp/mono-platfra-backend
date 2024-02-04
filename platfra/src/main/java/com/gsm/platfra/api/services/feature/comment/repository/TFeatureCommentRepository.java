package com.gsm.platfra.api.services.feature.comment.repository;

import com.gsm.platfra.api.entity.feature.TFeatureComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TFeatureCommentRepository extends JpaRepository<TFeatureComment, Long> {
}
