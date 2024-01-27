package com.gsm.platfra.api.services.board.repository.query;

import com.gsm.platfra.api.dto.platfraboard.PlatfraBoardDto;
import com.gsm.platfra.api.entity.platfraboard.TPlatfraBoard;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gsm.platfra.api.entity.platfraboard.QTPlatfraBoard.tPlatfraBoard;
import static com.gsm.platfra.api.entity.platfra.QTPlatfra.tPlatfra;
@Repository
@RequiredArgsConstructor
public class PlatfraBoardQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<PlatfraBoardDto> getList(PlatfraBoardDto platfraBoardDto){
        List<PlatfraBoardDto> list = queryFactory
                .select(
                        Projections.fields(
                                PlatfraBoardDto.class,
                                tPlatfraBoard.platfraBoardSeq,
                                tPlatfra.platfraId,
                                tPlatfraBoard.subject,
                                tPlatfraBoard.description,
                                tPlatfraBoard.delYn,
                                tPlatfraBoard.regUserId,
                                tPlatfraBoard.regDate,
                                tPlatfraBoard.modUserId,
                                tPlatfraBoard.modDate
                        )
                )
                .from(tPlatfraBoard)
                .leftJoin(tPlatfra).on(tPlatfraBoard.platfraId.eq(tPlatfra.platfraId))
                .where(
                        platfraBoardDto.getPlatfraBoardSeq() == null ? null : tPlatfraBoard.platfraBoardSeq.eq(platfraBoardDto.getPlatfraBoardSeq()),
                        platfraBoardDto.getPlatfraId().isBlank() ? null : tPlatfraBoard.platfraId.eq(platfraBoardDto.getPlatfraId()),
                        platfraBoardDto.getSubject().isBlank() ? null : tPlatfraBoard.subject.like("%"+platfraBoardDto.getSubject()+"%"),
                        platfraBoardDto.getRegUserId().isBlank() ? null : tPlatfraBoard.regUserId.like("%"+platfraBoardDto.getRegUserId()+"%"),
                        platfraBoardDto.getModUserId().isBlank() ? null : tPlatfraBoard.modUserId.like("%"+platfraBoardDto.getModUserId()+"%"),
                        platfraBoardDto.getDescription().isBlank() ? null : tPlatfraBoard.description.like("%"+platfraBoardDto.getDescription()+"%"),
                        tPlatfraBoard.delYn.eq(Boolean.FALSE)
                )
            .fetch();
        return list;
    }

    public long delete(Long platfraBoardSeq){
      return
          queryFactory
          .update(tPlatfraBoard)
          .set(tPlatfraBoard.delYn,Boolean.TRUE)
          .where(tPlatfraBoard.platfraBoardSeq.eq(platfraBoardSeq))
          .execute();
    }
}
