package com.gsm.platfra.api.services.account.info.service;

import com.gsm.platfra.api.data.account.info.AccountInfoDto;
import com.gsm.platfra.api.data.account.info.AccountInfoRepository;
import com.gsm.platfra.api.data.account.info.TAccountInfo;
import com.gsm.platfra.api.services.account.info.query.AccountInfoQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AccountInfoService {
    private final AccountInfoRepository accountInfoRepository;
    private final AccountInfoQueryRepository accountInfoQueryRepository;

    @Transactional
    public void create(AccountInfoDto accountInfoDto) {
        TAccountInfo accountInfo = AccountInfoDto.toEntity(accountInfoDto);
        accountInfoRepository.save(accountInfo);
    }

    @Transactional
    public void update(AccountInfoDto accountInfoDto) {
        accountInfoQueryRepository.update(accountInfoDto);
    }

    public AccountInfoDto get(String userId, String type) {
        return accountInfoQueryRepository.get(userId, type);
    }

}
