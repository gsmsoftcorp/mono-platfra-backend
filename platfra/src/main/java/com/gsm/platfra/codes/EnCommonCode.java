package com.gsm.platfra.codes;

import lombok.Getter;

@Getter
public enum EnCommonCode {

    CODE_200    (200);

    private int code;

    EnCommonCode( int code ){
        this.code = code;
    }

    public static EnCommonCode searchByCode( int code ){
        for( EnCommonCode enCommonCode : values() ){
            if( enCommonCode.getCode() == code ){
                return enCommonCode;
            }
        }
        return null;
    }

}
