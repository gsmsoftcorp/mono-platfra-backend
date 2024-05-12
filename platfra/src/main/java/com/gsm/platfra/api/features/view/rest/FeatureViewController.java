package com.gsm.platfra.api.features.view.rest;

import com.gsm.platfra.api.data.base.BaseResponse;
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

    /**
     * 조회수 증가
     * 배치로 따로 분리해야할 로직이 들어 있음
     * @param request
     * @param featureViewDto
     */
    @PostMapping
    public BaseResponse viewCount(HttpServletRequest request, @RequestBody FeatureViewDto featureViewDto) {
        // IP 주소 set
        featureViewDto.setAddress(request.getRemoteAddr());
        featureViewService.viewCount(featureViewDto);
        return BaseResponse.builder()
                .data(null)
                .code(null)
                .message(null)
                .error(null)
                .build();
    }

}
