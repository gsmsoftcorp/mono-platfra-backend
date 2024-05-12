package com.gsm.platfra.api.features.noti.rest;

import com.gsm.platfra.api.data.base.BaseResponse;
import com.gsm.platfra.api.features.noti.dto.NotificationDto;
import com.gsm.platfra.api.features.noti.service.TFeatureNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/notification")
@RequiredArgsConstructor
@RestController
public class TFeatureNotificationController {

    private final TFeatureNotificationService tFeatureNotificationService;

    @GetMapping
    public BaseResponse getNotifications() {
        return BaseResponse.builder()
                .data(tFeatureNotificationService.getNotifications())
                .code(null)
                .message(null)
                .error(null)
                .build();
    }

    @PostMapping("/test")
    public BaseResponse createTest(@RequestBody NotificationDto notificationDto) {
        tFeatureNotificationService.save(notificationDto);
        return BaseResponse.builder()
                .data(null)
                .code(null)
                .message(null)
                .error(null)
                .build();
    }
}
