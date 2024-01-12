package com.gsm.platfra.api.entity.account.utils;

import com.gsm.platfra.api.entity.account.TAccount;
import com.gsm.platfra.api.entity.account.repository.TAccountRepository;
import com.gsm.platfra.exception.BusinessLogicException;
import com.gsm.platfra.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Component
public class TAccountUtils implements TAccountVerificationManager {
    private final TAccountRepository tAccountRepository;

    @Override
    public TAccount get(String email) {
        Optional<TAccount> optionalTAccount =
                tAccountRepository.findByEmail(email);

        TAccount findTAccount =
                optionalTAccount.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.TACCOUNT_NOT_FOUND, String.format("%s 회원을 찾을 수 없습니다.", email)));
        checkIsActive(findTAccount);

        return findTAccount;
    }

    @Override
    public TAccount getById(Long tAccountId) {
        Optional<TAccount> optionalTAccount =
                tAccountRepository.findById(tAccountId);

        TAccount findTAccount =
                optionalTAccount.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.TACCOUNT_NOT_FOUND, "회원을 찾을 수 없습니다."));
        checkIsActive(findTAccount);

        return findTAccount;
    }

    @Override
    public List<TAccount> getList(List<String> uuids) {
        return tAccountRepository.findByUuidIn(uuids);
    }

    @Override
    public void checkIsActive(TAccount tAccount) {
        if (tAccount.getTAccountStatus() != TAccount.TAccountStatus.ACTIVE) {
            throw new BusinessLogicException(ExceptionCode.INACTIVE_TACCOUNT,
                    String.format("멤버(%s)는 활성화되지 않았습니다. 해당 요청을 처리할 수 없습니다.", tAccount.getEmail()));
        }
    }

    @Override
    public boolean isActive(TAccount tAccount) {
        if (tAccount.getTAccountStatus().equals(TAccount.TAccountStatus.ACTIVE)) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void checkEmailExists(String email) {
        Optional<TAccount> tAccount = tAccountRepository.findByEmail(email);
        if (tAccount.isPresent())
            throw new BusinessLogicException(ExceptionCode.TACCOUNT_EXISTS_EMAIL, String.format("%s는 이미 가입한 이메일입니다.", email));
    }

    @Override
    public void checkNicknameExists(String nickName) {
        Optional<TAccount> tAccount = tAccountRepository.findByNickName(nickName);
        if (tAccount.isPresent())
            throw new BusinessLogicException(ExceptionCode.TACCOUNT_EXISTS_NICKNAME, String.format("%s는 이미 사용중인 닉네임입니다.", nickName));
    }

    public TAccount getLoggedIn(String email) {
        return get(email);
    }

    public TAccount getLoggedInWithAuthenticationCheck(Authentication authentication) {
        if( authentication == null ) {
            throw new BusinessLogicException(ExceptionCode.AUTHENTICATION_NOT_NULL_ALLOWED);
        }

        return getLoggedIn(authentication.getPrincipal().toString());
    }
}
