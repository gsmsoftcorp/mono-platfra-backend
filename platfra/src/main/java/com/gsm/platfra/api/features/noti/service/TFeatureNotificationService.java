package com.gsm.platfra.api.features.noti.service;

import com.gsm.platfra.api.data.feature.noti.TFeatureNotification;
import com.gsm.platfra.api.features.noti.dto.NotificationDto;
import com.gsm.platfra.api.data.feature.noti.TFeatureNotificationRepository;
import com.gsm.platfra.api.features.noti.repository.query.TFeatureNotificationQueryRepository;
import com.gsm.platfra.system.security.context.UserContext;
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
        UserContext userContext = UserContextUtil.getUserContext();
        List<NotificationDto> userNotifications = tFeatureNotificationQueryRepository.getUserNotifications(userContext);
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
