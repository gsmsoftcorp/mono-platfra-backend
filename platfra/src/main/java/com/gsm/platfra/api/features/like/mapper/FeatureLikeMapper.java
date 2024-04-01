package com.gsm.platfra.api.features.like.mapper;

import com.gsm.platfra.api.data.feature.like.FeatureLikeDto;
import com.gsm.platfra.api.data.feature.like.TFeatureLike;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeatureLikeMapper {
    List<TFeatureLike> dtoListToEntityList(List<FeatureLikeDto> dtoList);
    List<FeatureLikeDto> entityListToDtoList(List<TFeatureLike> entityList);
    TFeatureLike dtoToEntity(FeatureLikeDto dto);
    FeatureLikeDto entityToDto(TFeatureLike entity);
}
