package com.gsm.platfra.config.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        logger.debug("authenticate()");
        String id = authentication.getName();
        String password = (String) authentication.getCredentials();

        logger.debug("authenticate() input id : " + id);
        logger.debug("authenticate() input password : " + password);

//        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        return new UsernamePasswordAuthenticationToken(accountResponse, null,
//                accountService.getGrantedAuthority( id ) );
//        return new UsernamePasswordAuthenticationToken(accountRes, null, authorities);
        return null;
    }

    @Override
    public boolean supports(Class authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
