package com.gsm.platfra.api.data.feature.like;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeatureLikeDto {
    private Long featureLikeSeq;
    @NotBlank(message = "컨텐츠 종류를 입력해 주세요.")
    private String contentsCd;
    @NotNull(message = "컨텐츠 번호를 입력해 주세요.")
    private Long contentsSeq;
    private Boolean likeYn;
    private String userId;
    private String regUserId;
    private String modUserId;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public static FeatureLikeDto of(TFeatureLike like){
        return FeatureLikeDto.builder()
            .featureLikeSeq(like.getFeatureLikeSeq())
            .contentsCd(like.getContentsCd())
            .contentsSeq(like.getContentsSeq())
            .likeYn(like.getLikeYn())
            .userId(like.getUserId())
            .regUserId(like.getRegUserId())
            .modUserId(like.getModUserId())
            .regDate(like.getRegDate())
            .modDate(like.getModDate())
            .build();
    }

    public static TFeatureLike toEntity(FeatureLikeDto dto){
        TFeatureLike like = TFeatureLike.builder()
            .featureLikeSeq(dto.getFeatureLikeSeq())
            .contentsCd(dto.getContentsCd())
            .contentsSeq(dto.getContentsSeq())
            .likeYn(dto.getLikeYn())
            .userId(dto.getUserId())
            .build();
        like.setRegUserId(dto.getRegUserId());
        like.setModUserId(dto.getModUserId());
        like.setRegDate(dto.getRegDate());
        like.setModDate(dto.getModDate());
        return like;
    }
}
