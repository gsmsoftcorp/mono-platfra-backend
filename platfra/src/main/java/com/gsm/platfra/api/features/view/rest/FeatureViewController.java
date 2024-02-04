package com.gsm.platfra.api.features.view.rest;

import com.gsm.platfra.api.data.feature.view.FeatureViewDto;
import com.gsm.platfra.api.features.view.service.FeatureViewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/view")
@RequiredArgsConstructor
public class FeatureViewController {

    private final FeatureViewService featureViewService;

    @PostMapping
    public void viewCount(HttpServletRequest request, @RequestBody FeatureViewDto featureViewDto) {
        String address = request.getRemoteAddr();
        featureViewDto.setAddress(address);
        featureViewService.viewCount(featureViewDto);
    }

}
