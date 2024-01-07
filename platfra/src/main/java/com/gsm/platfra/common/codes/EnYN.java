package com.gsm.platfra.common.codes;

import lombok.Getter;

@Getter
public enum EnYN {

    Y   ("Y"),
    N   ("N");

    private String code;

    EnYN( String code ){
        this.code = code;
    }

    public static EnYN searchByCode(String code){
        for(EnYN enYN : values()){
            if( enYN.getCode().equals(code)){
                return enYN;
            }
        }
        return null;
    }

}
