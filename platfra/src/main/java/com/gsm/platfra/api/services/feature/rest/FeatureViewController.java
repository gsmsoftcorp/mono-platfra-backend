package com.gsm.platfra.api.services.feature.rest;

import com.gsm.platfra.api.services.feature.service.FeatureViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/view")
@RequiredArgsConstructor
public class FeatureViewController {

    private final FeatureViewService featureViewService;

    @GetMapping("/{contentsCd}/{contentsSeq}")
    public void viewCount(@PathVariable String contentsCd, @PathVariable Long contentsSeq) {
        String userId = "";
        featureViewService.viewCount(contentsCd, contentsSeq, userId);
    }
}
