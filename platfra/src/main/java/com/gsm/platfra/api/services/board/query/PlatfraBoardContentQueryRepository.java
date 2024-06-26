package com.gsm.platfra.api.services.board.query;

import com.gsm.platfra.api.data.platfraboard.PlatfraBoardContentDto;
import com.gsm.platfra.api.features.view.dto.FeatureViewCountDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gsm.platfra.api.data.platfraboard.QTPlatfraBoardContent.tPlatfraBoardContent;


@Repository
@RequiredArgsConstructor
public class PlatfraBoardContentQueryRepository {
  private final JPAQueryFactory queryFactory;

  public List<PlatfraBoardContentDto> getList(PlatfraBoardContentDto contentDto){
    return
        queryFactory
            .select(Projections.fields(
                PlatfraBoardContentDto.class,
                tPlatfraBoardContent.contentSeq,
                tPlatfraBoardContent.platfraBoardSeq,
                tPlatfraBoardContent.contentNo,
                tPlatfraBoardContent.title,
                tPlatfraBoardContent.content,
                tPlatfraBoardContent.view
              )
            ).from(tPlatfraBoardContent)
            .where(
                contentDto.getPlatfraBoardSeq() == null ? null : tPlatfraBoardContent.platfraBoardSeq.eq(contentDto.getPlatfraBoardSeq())
//                contentDto.getContentSeq() == null ? null : tPlatfraBoardContent.contentSeq.eq(contentDto.getContentSeq()),
//                contentDto.getContentNo() == null ? null : tPlatfraBoardContent.contentNo.eq(contentDto.getContentNo()),
//                contentDto.getTitle() == null ? null : tPlatfraBoardContent.title.like("%"+contentDto.getTitle()+"%"),
//                contentDto.getContent() == null ? null : tPlatfraBoardContent.content.like("%"+contentDto.getContent()+"%")
            )
            .fetch();
  }

  public List<PlatfraBoardContentDto> getMyBoardContent(String userId, Pageable pageable) {
      return
              queryFactory
                      .select(Projections.fields(
                                      PlatfraBoardContentDto.class,
                                      tPlatfraBoardContent.platfraBoardSeq,
                                      tPlatfraBoardContent.contentSeq,
                                      tPlatfraBoardContent.contentNo,
                                      tPlatfraBoardContent.title,
                                      tPlatfraBoardContent.content,
                                      tPlatfraBoardContent.regUserId,
                                      tPlatfraBoardContent.regDate,
                                      tPlatfraBoardContent.modUserId,
                                      tPlatfraBoardContent.modDate
                              )
                      ).from(tPlatfraBoardContent)
                      .where(
                                tPlatfraBoardContent.regUserId.eq(userId)
                      )
                      .offset(pageable.getOffset())
                      .limit(pageable.getPageSize())
                      .fetch();
  }

  public long delete (Long contentSeq){
    return
        queryFactory
            .update(tPlatfraBoardContent)
            .set(tPlatfraBoardContent.delYn,Boolean.TRUE)
            .where(tPlatfraBoardContent.contentSeq.eq(contentSeq))
            .execute();
  }
    public void updateCount(FeatureViewCountDto dto){
        queryFactory
                .update(tPlatfraBoardContent)
                .set(tPlatfraBoardContent.view, tPlatfraBoardContent.view.add(dto.getCount()))
                .where(tPlatfraBoardContent.contentSeq.eq(dto.getContentsSeq()))
                .execute();
    }
}
