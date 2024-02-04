package com.gsm.platfra.api.features.noti.rest;

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
    public List<NotificationDto> getNotifications() {
        return tFeatureNotificationService.getNotifications();
    }

    @PostMapping("/test")
    public void createTest(@RequestBody NotificationDto notificationDto) {
        tFeatureNotificationService.save(notificationDto);
    }
}
