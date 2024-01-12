package com.gsm.platfra.security.userdetails;

import com.gsm.platfra.api.entity.account.TAccount;
import com.gsm.platfra.api.entity.account.repository.TAccountRepository;
import com.gsm.platfra.exception.BusinessLogicException;
import com.gsm.platfra.exception.ExceptionCode;
import com.gsm.platfra.security.utils.CustomAuthorityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class TAccountDetailsService implements UserDetailsService {

    private final TAccountRepository tAccountRepository;
    private final CustomAuthorityUtils authorityUtils;

    public TAccountDetailsService(TAccountRepository tAccountRepository, CustomAuthorityUtils authorityUtils) {
        this.tAccountRepository = tAccountRepository;
        this.authorityUtils = authorityUtils;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<TAccount> optionalTAccount = tAccountRepository.findByEmail(email);
        TAccount findTAccount = optionalTAccount.orElseThrow(() -> new BusinessLogicException(ExceptionCode.TACCOUNT_NOT_FOUND));

        if(!findTAccount.getTAccountStatus().equals(TAccount.TAccountStatus.ACTIVE)){
            throw new BusinessLogicException(ExceptionCode.INACTIVE_TACCOUNT);
        }
        return new TAccountDetails(findTAccount);
    }

    private final class TAccountDetails extends TAccount implements UserDetails {
        TAccountDetails(TAccount tAccount) {
            setId(tAccount.getId());
            setEmail(tAccount.getEmail());
            setPassword(tAccount.getPassword());
            setNickName(tAccount.getNickName());
            setRoles(tAccount.getRoles());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getRoles());
        }

        @Override
        public String getUsername() {
            return getEmail();
        }



        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
