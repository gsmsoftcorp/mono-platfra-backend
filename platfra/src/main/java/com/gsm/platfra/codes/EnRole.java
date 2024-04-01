package com.gsm.platfra.codes;

import lombok.Getter;

@Getter
public enum EnRole {

    ADMIN   ("ADMIN", "어드민"),
    USER    ("USER", "일반 유저");

    private String code;
    private String des;

    EnRole( String code, String des ){
        this.code = code;
        this.des = des;
    }

    public static EnRole searchByCode(String code){
        for(EnRole enYN : values()){
            if( enYN.getCode().equals(code)){
                return enYN;
            }
        }
        return null;
    }

}
