package com.gsm.platfra.api.services.account.info.rest;

import com.gsm.platfra.api.data.account.info.AccountInfoDto;
import com.gsm.platfra.api.services.account.info.service.AccountInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 유저 프로필
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account/info")
public class AccountInfoController {

    private final AccountInfoService accountInfoService;

    @PostMapping
    public void create(@RequestBody AccountInfoDto accountInfoDto) {
        // TODO : 프로필은 회원 가입하면 기본 프로필로 저장이 되어있어야 하고 업데이트 기능만 있어야 될 듯?
        // 사업자 등록 시 사용? type 설정
        accountInfoService.create(accountInfoDto);
    }
    @PutMapping
    public void update(@RequestBody AccountInfoDto accountInfoDto) {
        // 프로필 사진은 common file 등록 후 seq 받아오기?
        accountInfoService.update(accountInfoDto);
    }

    @GetMapping("/{userId}/{type}")
    public AccountInfoDto get(@PathVariable String userId, @PathVariable String type) {
        return accountInfoService.get(userId, type);
    }
}
