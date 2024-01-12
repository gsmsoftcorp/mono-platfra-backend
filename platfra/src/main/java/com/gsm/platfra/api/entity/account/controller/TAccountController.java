package com.gsm.platfra.api.entity.account.controller;

import com.gsm.platfra.api.entity.account.TAccount;
import com.gsm.platfra.api.entity.account.dto.TAccountDto;
import com.gsm.platfra.api.entity.account.dto.TAccountJoinResponseDto;
import com.gsm.platfra.api.entity.account.mapper.TAccountMapper;
import com.gsm.platfra.api.entity.account.service.TAccountService;
import com.gsm.platfra.api.entity.account.utils.TAccountUtils;
import com.gsm.platfra.api.entity.common.dto.MultiResponseDto;
import com.gsm.platfra.security.dto.LoginDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/tAccounts")
@Validated
@Slf4j
public class TAccountController {
    private final TAccountService tAccountService;
    private final TAccountMapper tAccountMapper;
    private final TAccountUtils tAccountUtils;

    @PostMapping
    public ResponseEntity postTAccount(@Valid @RequestBody TAccountDto.Post requestBody) {
        TAccount tAccount = tAccountMapper.tAccountPostToTAccount(requestBody);
        TAccount createTAccount = tAccountService.createTAccount(tAccount);
        TAccountJoinResponseDto responseDto = tAccountMapper.tAccountToTAccountResponse(createTAccount);

        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity patchTAccount(
            @Valid @RequestBody TAccountDto.Patch requestBody,
            Authentication authentication) {
        TAccount updateTAccount = tAccountService.updateTAccount(tAccountMapper.tAccountPatchToTAccount(requestBody), authentication.getName());
        TAccountJoinResponseDto responseDto = tAccountMapper.tAccountToTAccountResponse(updateTAccount);

        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTAccount(Authentication authentication) {
        TAccount tAccount = tAccountUtils.get(authentication.getName());
        TAccountJoinResponseDto responseDto = tAccountMapper.tAccountToTAccountResponse(tAccount);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity getTAccountsByUUID(@RequestBody TAccountDto.UuidListRequest uuidListRequestDto) {
        List<TAccount> tAccounts = tAccountUtils.getList(uuidListRequestDto.getData());
        List<TAccountJoinResponseDto> responseDtos = tAccounts.stream()
                .map(tAccountMapper::tAccountToTAccountResponse)
                .collect(Collectors.toList());
        return new ResponseEntity(responseDtos, HttpStatus.OK);
    }


    @GetMapping("/all") // Todo 개발용으로 만들어 뒀다. 후에 삭제!!
    public ResponseEntity getTAccounts(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<TAccount> pageTAccounts = tAccountService.findTAccounts(page - 1, size);
        List<TAccount> tAccounts = pageTAccounts.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(tAccountMapper.tAccountsToTAccountResponses(tAccounts),
                        pageTAccounts),
                HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteTAccount(Authentication authentication) {
        tAccountService.deleteTAccount(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/profile-image")
    public ResponseEntity updateProfileImage(
            @RequestBody TAccountDto.ProfileImage requestBody,
            Authentication authentication) {
        TAccount updatedTAccount = tAccountService.updateTAccount(tAccountMapper.tAccountProfileImageToTAccount(requestBody), authentication.getName());
        TAccountJoinResponseDto responseDto = tAccountMapper.tAccountToTAccountResponse(updatedTAccount);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @PostMapping("/password")
    public ResponseEntity checkPassword(@RequestBody TAccountDto.CheckPassword requestBody,
                                        Authentication authentication) {
        boolean isPasswordCorrect = tAccountService.checkPassword(requestBody.getPassword(), authentication.getName());
        if (isPasswordCorrect) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/provider")
    public ResponseEntity checkProvider(Authentication authentication){
        Map<String,String> response = tAccountService.checkProvider(authentication.getName());

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/reactive")
    public ResponseEntity reactive(@RequestBody LoginDto loginDto){
        TAccount tAccount = tAccountService.reactive(loginDto.getEmail(),loginDto.getPassword());
        TAccountJoinResponseDto responseDto = tAccountMapper.tAccountToTAccountResponse(tAccount);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

}
