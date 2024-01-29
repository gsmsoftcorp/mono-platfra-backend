package com.gsm.platfra.api.services.feature.service;

import com.gsm.platfra.api.services.feature.repository.TFeatureViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class FeatureViewService {

    private final TFeatureViewRepository tFeatureViewRepository;


    public void viewCount(String contentsCd, Long contentsSeq, String userId) {

    }

}
