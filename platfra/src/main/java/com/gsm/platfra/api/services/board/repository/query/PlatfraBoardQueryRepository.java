package com.gsm.platfra.api.services.board.repository.query;

import com.gsm.platfra.api.data.platfraboard.PlatfraBoardDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gsm.platfra.api.data.platfraboard.QTPlatfraBoard.tPlatfraBoard;

@Repository
@RequiredArgsConstructor
public class PlatfraBoardQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<PlatfraBoardDto> getList(PlatfraBoardDto platfraBoardDto){
        return queryFactory
                .select(
                        Projections.fields(
                                PlatfraBoardDto.class,
                                tPlatfraBoard.platfraBoardSeq,
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
                .where(
                        platfraBoardDto.getPlatfraBoardSeq() == null ? null : tPlatfraBoard.platfraBoardSeq.eq(platfraBoardDto.getPlatfraBoardSeq()),
                        platfraBoardDto.getPlatfraId().isBlank() ? null : tPlatfraBoard.platfraId.eq(platfraBoardDto.getPlatfraId()),
                        platfraBoardDto.getSubject().isBlank() ? null : tPlatfraBoard.subject.like("%"+platfraBoardDto.getSubject()+"%"),
                        platfraBoardDto.getDescription().isBlank() ? null : tPlatfraBoard.description.like("%"+platfraBoardDto.getDescription()+"%")
                )
            .fetch();
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
