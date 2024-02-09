package com.gsm.platfra.api.services.platfra.query;

import com.gsm.platfra.api.data.platfra.saved.ContentSaveDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gsm.platfra.api.data.platfra.saved.QContentSave.contentSave;

@Repository
@RequiredArgsConstructor
public class ContentSaveQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<ContentSaveDto> getMyContentList(String userId) {
        List<ContentSaveDto> list = queryFactory.select(
                        Projections.fields(
                                ContentSaveDto.class,
                                contentSave.contentSaveSeq,
                                contentSave.userId,
                                contentSave.platfraId,
                                contentSave.contentSeq,
                                contentSave.platfraBoardSeq
                        )
                )
                .from(contentSave)
                .where(contentSave.userId.eq(userId))
                .fetch();

        return list;
    }

    public void deleteContent(Long contentSaveSeq) {
        queryFactory.update(contentSave)
                .set(contentSave.delYn, true)
                .where(contentSave.contentSaveSeq.eq(contentSaveSeq))
                .execute();
    }
}
