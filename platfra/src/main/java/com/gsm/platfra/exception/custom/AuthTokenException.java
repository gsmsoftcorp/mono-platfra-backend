package com.gsm.platfra.exception.custom;

import com.gsm.platfra.exception.BaseException;
import lombok.Getter;

@Getter
public class AuthTokenException extends BaseException {

    public AuthTokenException(String message) {
        super(message);
    }

}
