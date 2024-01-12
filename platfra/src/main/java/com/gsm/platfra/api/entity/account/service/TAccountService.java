package com.gsm.platfra.api.entity.account.service;

import com.gsm.platfra.api.entity.account.TAccount;
import com.gsm.platfra.api.entity.account.repository.TAccountRepository;
import com.gsm.platfra.api.entity.account.utils.NickNameValidationUtility;
import com.gsm.platfra.api.entity.account.utils.TAccountUtils;
import com.gsm.platfra.exception.BusinessLogicException;
import com.gsm.platfra.exception.ExceptionCode;
import com.gsm.platfra.security.utils.CustomAuthorityUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Transactional
@Service
public class TAccountService {
    private final TAccountRepository tAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;
    private final TAccountUtils tAccountUtils;
    private final NickNameValidationUtility nickNameValidationUtility;

    public TAccount createTAccount(TAccount tAccount) {
        nickNameValidationUtility.checkValidNickName(tAccount.getNickName());

        Optional<TAccount> optionalTAccount =
                tAccountRepository.findByEmail(tAccount.getEmail());
        if(optionalTAccount.isPresent()){
            TAccount findTAccount = optionalTAccount.get();
            if(tAccountUtils.isActive(findTAccount)){
                throw new BusinessLogicException(ExceptionCode.TACCOUNT_EXISTS_EMAIL);
            }else if(!tAccountUtils.isActive(findTAccount)){
                throw new BusinessLogicException(ExceptionCode.INACTIVE_TACCOUNT);
            }
        }

        tAccountUtils.checkEmailExists(tAccount.getEmail());
        tAccountUtils.checkNicknameExists(tAccount.getNickName());

        String encryptedPassword = passwordEncoder.encode(tAccount.getPassword());
        tAccount.setPassword(encryptedPassword);

        List<String> roles = authorityUtils.createRoles(tAccount.getEmail());
        tAccount.setRoles(roles);

        if (tAccount.getProfileImage() == null || tAccount.getProfileImage().isEmpty()) {
            tAccount.setProfileImage("https://www.gravatar.com/avatar/HASH");
        }

        TAccount savedTAccount = tAccountRepository.save(tAccount);

        return savedTAccount;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public TAccount updateTAccount(TAccount tAccount, String email) {

        TAccount findTAccount = tAccountUtils.get(email);
        tAccountUtils.checkNicknameExists(tAccount.getNickName());

        Optional.ofNullable(tAccount.getNickName())
                .ifPresent(name -> {
                    if (!name.isEmpty()) {
                        findTAccount.setNickName(name);
                    }
                });
        Optional.ofNullable(tAccount.getPassword())
                .ifPresent(password -> {
                    if (!password.isEmpty()) {
                        findTAccount.setPassword(passwordEncoder.encode(password));
                    }
                });
        Optional.ofNullable(tAccount.getProfileImage())
                .ifPresent(image -> findTAccount.setProfileImage(image));
        return tAccountRepository.save(findTAccount);
    }

    public Page<TAccount> findTAccounts(int page, int size) {
        return tAccountRepository.findAll(PageRequest.of(page, size,
                Sort.by("id").descending()));
    }

    public void deleteTAccount(String email) {
        TAccount findTAccount = tAccountUtils.get(email);

        findTAccount.setTAccountStatus(TAccount.TAccountStatus.QUIT);

        tAccountRepository.save(findTAccount);
    }

    public boolean checkPassword(String password, String email){
        TAccount tAccount = tAccountUtils.get(email);
        return passwordEncoder.matches(password, tAccount.getPassword());
    }

    public Map<String, String> checkProvider(String email){
        TAccount tAccount = tAccountUtils.get(email);

        String provider;

        if(tAccount.getProvider()==TAccount.Provider.LOCAL){
            provider = "LOCAL";
        }else if(tAccount.getProvider()==TAccount.Provider.GOOGLE){
            provider = "GOOGLE";
        }else if(tAccount.getProvider()==TAccount.Provider.NAVER){
            provider = "NAVER";
        }else if(tAccount.getProvider()==TAccount.Provider.KAKAO){
            provider = "KAKAO";
        }else{
            provider = "Error";
        }

        Map<String, String> response = new HashMap<>();
        response.put("provider", provider);

        return response;
    }

    public TAccount reactive(String email, String password){
        Optional<TAccount> optionalTAccount =
                tAccountRepository.findByEmail(email);

        TAccount tAccount =
                optionalTAccount.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.TACCOUNT_NOT_FOUND, String.format("%s 회원을 찾을 수 없습니다.", email)));


        if(passwordEncoder.matches(password, tAccount.getPassword()) && !tAccount.getTAccountStatus().equals(TAccount.TAccountStatus.ACTIVE)){
            tAccount.setTAccountStatus(TAccount.TAccountStatus.ACTIVE);
            tAccountRepository.save(tAccount); // 이게 없어도 JPA Dirty Checking 기능으로 인해 변경사항이 db에 자동으로 저장된다.
        }else if(tAccount.getTAccountStatus().equals(TAccount.TAccountStatus.ACTIVE)){
            throw new BusinessLogicException(ExceptionCode.TACCOUNT_ALREADY_ACTIVE);
        }else if(!passwordEncoder.matches(password, tAccount.getPassword())){
            throw new BusinessLogicException(ExceptionCode.TACCOUNT_PASSWORD_ERROR);
        }

        return tAccount;
    }
}
