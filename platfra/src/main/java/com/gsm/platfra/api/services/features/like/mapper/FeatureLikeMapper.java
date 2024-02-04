package com.gsm.platfra.api.services.features.like.mapper;

import com.gsm.platfra.api.data.feature.like.TFeatureLike;
import com.gsm.platfra.api.dto.feat.like.FeatureLikeDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeatureLikeMapper {
    List<TFeatureLike> dtoListToEntityList(List<FeatureLikeDto> dtoList);
    List<FeatureLikeDto> entityListToDtoList(List<TFeatureLike> entityList);
    TFeatureLike dtoToEntity(FeatureLikeDto dto);
    FeatureLikeDto entityToDto(TFeatureLike entity);
}
