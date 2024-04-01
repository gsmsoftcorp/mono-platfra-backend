package com.gsm.platfra.api.services.platfra.service;

import com.gsm.platfra.api.data.account.TAccount;
import com.gsm.platfra.api.data.account.TAccountRepository;
import com.gsm.platfra.api.data.platfra.TPlatfra;
import com.gsm.platfra.api.data.platfra.TPlatfraRepository;
import com.gsm.platfra.api.data.platfra.saved.ContentSave;
import com.gsm.platfra.api.data.platfra.saved.ContentSaveDto;
import com.gsm.platfra.api.data.platfra.saved.ContentSaveRepository;
import com.gsm.platfra.api.services.platfra.query.ContentSaveQueryRepository;
import com.gsm.platfra.exception.ExceptionCode;
import com.gsm.platfra.system.security.context.UserContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ContentSaveService {
    private final TPlatfraRepository tPlatfraRepository;
    private final ContentSaveQueryRepository contentSaveQueryRepository;

    private final ContentSaveRepository contentSaveRepository;
    private final TAccountRepository tAccountRepository;
    private final Long DEFAULT_BOARD_SEQ = 0L;

    @Transactional
    public void saveContent(String platfraId, Long platfraBoardSeq, Long contentSeq) {
        TPlatfra tPlatfra = tPlatfraRepository.findTPlatfraByPlatfraId(platfraId)
                .orElseThrow(() -> new IllegalArgumentException(ExceptionCode.NOT_FOUND_PLATFORM.getMessage()));
        String userId = UserContextUtil.getUserContext().getUserId();

        ContentSave contentSave = ContentSave.builder()
                .userId(userId)
                .platfra(tPlatfra)
                .platfraBoardSeq(platfraBoardSeq)
                .contentSeq(contentSeq)
                .build();

        contentSaveRepository.save(contentSave);
    }

    @Transactional
    public void saveContent(String platfraId, Long contentSeq) {
        TPlatfra tPlatfra = tPlatfraRepository.findTPlatfraByPlatfraId(platfraId)
                .orElseThrow(() -> new IllegalArgumentException(ExceptionCode.NOT_FOUND_PLATFORM.getMessage()));
        String userId = UserContextUtil.getUserContext().getUserId();

        ContentSave contentSave = ContentSave.builder()
                .userId(userId)
                .platfra(tPlatfra)
                .contentSeq(contentSeq)
                .platfraBoardSeq(DEFAULT_BOARD_SEQ)
                .build();

        contentSaveRepository.save(contentSave);
    }

    public List<ContentSaveDto> getMyContentList(String userId) {
        TAccount tAccount = tAccountRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException(ExceptionCode.NOT_FOUND_USER.getMessage()));
        if (!tAccount.getUserId().equals(UserContextUtil.getUserContext().getUserId())) {
            throw new IllegalCallerException(ExceptionCode.AUTHORIZATION_FAILED.getMessage());
        }

        return contentSaveQueryRepository.getMySavedContentList(userId);
    }

    @Transactional
    public void deleteContent(String userId, Long contentSaveSeq) {
        TAccount tAccount = tAccountRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException(ExceptionCode.NOT_FOUND_USER.getMessage()));
        if (!tAccount.getUserId().equals(UserContextUtil.getUserContext().getUserId())) {
            throw new IllegalCallerException(ExceptionCode.AUTHORIZATION_FAILED.getMessage());
        }
        contentSaveQueryRepository.deleteContent(contentSaveSeq);
    }

}
