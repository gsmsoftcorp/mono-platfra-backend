package com.gsm.platfra.api.services.feature.service;

import com.gsm.platfra.api.dto.feature.FeatureViewDto;
import com.gsm.platfra.api.entity.feature.TFeatureView;
import com.gsm.platfra.api.services.feature.dto.FeatureViewCountDto;
import com.gsm.platfra.api.services.feature.repository.TFeatureViewRepository;
import com.gsm.platfra.api.services.feature.repository.query.TCommonCodeQueryRepository;
import com.gsm.platfra.api.services.feature.repository.query.TFeatureViewQueryRepository;
import com.gsm.platfra.api.services.feature.repository.query.TPlatfraBoardContentQueryRepository;
import com.gsm.platfra.api.services.feature.repository.query.TPlatfraContentQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class FeatureViewService {

    private final TFeatureViewRepository tFeatureViewRepository;
    private final TFeatureViewQueryRepository tFeatureViewQueryRepository;
    private final TCommonCodeQueryRepository tCommonCodeQueryRepository;
    private final TPlatfraContentQueryRepository tPlatfraContentQueryRepository;
    private final TPlatfraBoardContentQueryRepository tPlatfraBoardContentQueryRepository;

    @Transactional
    public void viewCount(FeatureViewDto featureViewDto) {
        // content에 해당 userId가 동일 ip에서 접근한 적 있는지 체크
        if(tFeatureViewQueryRepository.isDuplicateAccess(featureViewDto) == null){
            TFeatureView tFeatureView = FeatureViewDto.toEntity(featureViewDto);
            tFeatureViewRepository.save(tFeatureView);

            /**
             * 배치 영역으로 분리해야될 로직
             * 일정 시간동안 content 그룹별 몇 번 증가 했는지 가져와서 해당 content viewCount 증가
             *
             */
            ////////////
            // 1. contentCd 가져오기
            // 2. contentCd 별로 seq groupBy해서 count 가져오기
            // 3. 받은 값들에 해당하는 content에 viewCount Update
            List<String> contentList = tCommonCodeQueryRepository.getContentsInfo();
            contentList.forEach(contentCd -> {
                List<FeatureViewCountDto> viewCountDtoList = tFeatureViewQueryRepository.getAddViewList(contentCd);
                // 컨텐츠별 조회수 증가
                updateCount(viewCountDtoList, contentCd);
            });
            ////////////
        }
    }

    private void updateCount(List<FeatureViewCountDto> viewCountDtoList, String contentCd) {
        switch (contentCd) {
            case "PLATFRA_CONTENT" -> viewCountDtoList.forEach(tPlatfraContentQueryRepository::updateCount);
            case "BOARD_CONTENT" -> viewCountDtoList.forEach(tPlatfraBoardContentQueryRepository::updateCount);
            default -> log.error("업데이트 대상 Code가 아님.");
        }
    }

}
