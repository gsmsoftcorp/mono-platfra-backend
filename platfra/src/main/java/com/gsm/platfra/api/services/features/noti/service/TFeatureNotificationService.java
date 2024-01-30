package com.gsm.platfra.api.services.features.noti.service;

import com.gsm.platfra.api.entity.feature.TFeatureNotification;
import com.gsm.platfra.api.services.features.noti.dto.NotificationDto;
import com.gsm.platfra.api.services.features.noti.repository.TFeatureNotificationRepository;
import com.gsm.platfra.api.services.features.noti.repository.query.TFeatureNotificationQueryRepository;
import com.gsm.platfra.system.security.context.UserContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TFeatureNotificationService {

    private final TFeatureNotificationRepository tFeatureNotificationRepository;
    private final TFeatureNotificationQueryRepository tFeatureNotificationQueryRepository;

    public List<NotificationDto> getNotifications() {
        String userId = UserContextUtil.getUserContext().getUserId();
        List<NotificationDto> userNotifications = tFeatureNotificationQueryRepository.getUserNotifications(userId);
        return userNotifications;
    }

    @Transactional
    public void save(NotificationDto dto) {
        TFeatureNotification notification = NotificationDto.toEntity(dto);
        tFeatureNotificationRepository.save(notification);
    }

    @Transactional
    public void updateReadYn(Long featureNotificationSeq) {
        TFeatureNotification notification = tFeatureNotificationRepository.findById(featureNotificationSeq).orElseThrow();
        notification.updateReadYn();
    }

    @Transactional
    public void updateSendYn(Long featureNotificationSeq) {
        TFeatureNotification notification = tFeatureNotificationRepository.findById(featureNotificationSeq).orElseThrow();
        notification.updateSendYn();
    }
}
