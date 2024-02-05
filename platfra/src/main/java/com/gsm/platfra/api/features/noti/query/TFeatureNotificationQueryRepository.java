package com.gsm.platfra.api.features.noti.query;

import com.gsm.platfra.api.features.noti.dto.NotificationDto;
import com.gsm.platfra.system.security.context.UserContext;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gsm.platfra.api.data.feature.noti.QTFeatureNotification.tFeatureNotification;


@RequiredArgsConstructor
@Repository
public class TFeatureNotificationQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<NotificationDto> getUserNotifications(UserContext userContext) {
        return queryFactory
                .select(Projections.constructor(
                        NotificationDto.class,
                        tFeatureNotification.featureNotiSeq,
                        tFeatureNotification.contentsCd,
                        tFeatureNotification.contentsSeq,
                        tFeatureNotification.featureSeq,
                        tFeatureNotification.notiTargetUserId,
                        tFeatureNotification.notiTitle,
                        tFeatureNotification.notiDescription,
                        tFeatureNotification.notiUrl
                ))
                .from(tFeatureNotification)
                .where(
                        tFeatureNotification.notiTargetUserId.eq(userContext.getUserId()),
                        tFeatureNotification.readYn.eq(false),
                        tFeatureNotification.sendYn.eq(false)
                )
                .fetch();
    }

}
