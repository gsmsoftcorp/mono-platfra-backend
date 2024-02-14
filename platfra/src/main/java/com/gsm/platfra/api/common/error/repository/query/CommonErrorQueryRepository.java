package com.gsm.platfra.api.common.error.repository.query;

import com.gsm.platfra.api.data.common.error.CommonErrorDto;
import com.gsm.platfra.api.data.common.error.QTCommonError;
import com.gsm.platfra.api.data.platfra.PlatfraContentDto;
import com.gsm.platfra.api.data.platfra.PlatfraDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gsm.platfra.api.data.common.code.QTCommonCode.tCommonCode;
import static com.gsm.platfra.api.data.common.error.QTCommonError.tCommonError;
import static com.gsm.platfra.api.data.feature.like.QTFeatureLike.tFeatureLike;
import static com.gsm.platfra.api.data.feature.view.QTFeatureView.tFeatureView;
import static com.gsm.platfra.api.data.platfra.QTPlatfra.tPlatfra;
import static com.gsm.platfra.api.data.platfra.QTPlatfraContent.tPlatfraContent;
import static com.gsm.platfra.api.data.platfra.category.QTPlatfraCategory.tPlatfraCategory;


/**
 *
 */
@Repository
@RequiredArgsConstructor
public class CommonErrorQueryRepository {
    private final JPAQueryFactory queryFactory;

//    public void save(CommonErrorDto commonErrorDto) {
//        queryFactory
//                .insert(tCommonError)
//                .columns(tCommonError.errorMsg, tCommonError.location)
//                .values(commonErrorDto.getErrorMsg(), commonErrorDto.getLocation())
//                .execute();
//    }

//    public void update(CommonErrorDto commonErrorDto) {
//        queryFactory
//                .update(tCommonError)
//                .set(tCommonError.resData, commonErrorDto.getResData())
//
//    }

}























