package com.gsm.platfra.api.services.board.repository.query;

import com.gsm.platfra.api.dto.platfraboard.PlatfraBoardContentDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.gsm.platfra.api.entity.platfraboard.QTPlatfraBoardContent.tPlatfraBoardContent;
import static com.gsm.platfra.api.entity.platfraboard.QTPlatfraBoard.tPlatfraBoard;
@Repository
@RequiredArgsConstructor
public class PlatfraBoardContentQueryRepository {
  private final JPAQueryFactory queryFactory;

  public List<PlatfraBoardContentDto> getList (PlatfraBoardContentDto contentDto){
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
                contentDto.getPlatfraBoardSeq() == null ? null : tPlatfraBoardContent.platfraBoardSeq.eq(contentDto.getPlatfraBoardSeq()),
                contentDto.getContentSeq() == null ? null : tPlatfraBoardContent.contentSeq.eq(contentDto.getContentSeq()),
                contentDto.getContentNo() == null ? null : tPlatfraBoardContent.contentNo.eq(contentDto.getContentNo()),
                contentDto.getTitle() == null ? null : tPlatfraBoardContent.title.like("%"+contentDto.getTitle()+"%"),
                contentDto.getContent() == null ? null : tPlatfraBoardContent.content.like("%"+contentDto.getContent()+"%"),
                contentDto.getRegUserId() == null ? null : tPlatfraBoardContent.regUserId.like("%"+contentDto.getRegUserId()+"%"),
                tPlatfraBoardContent.delYn.eq(Boolean.FALSE)
            )
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

}
