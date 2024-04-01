package com.gsm.platfra.exception.custom;

import io.jsonwebtoken.JwtException;
import lombok.Getter;

@Getter
public class AuthTokenException extends JwtException {

    public AuthTokenException(String message) {
        super(message);
    }

    public AuthTokenException(String message, Throwable cause) {
        super(message, cause);
    }

}
