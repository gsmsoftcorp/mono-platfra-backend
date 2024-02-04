package com.gsm.platfra.api.services.feature.view.rest;

import com.gsm.platfra.api.dto.feature.FeatureViewDto;
import com.gsm.platfra.api.services.feature.view.service.FeatureViewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


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
