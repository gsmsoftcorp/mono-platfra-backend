package com.gsm.platfra.common.codes;

import lombok.Getter;

@Getter
public enum EnEmailName {

    AUTH    ("email_auth", "계정 인증 메일"),
    FIND_ID ("email_find_id", "ID 찾기 메일"),
    FIND_PW ("email_find_pw", "PW 찾기 메일");

    private String fileName;
    private String mailTitle;

    EnEmailName( String fileName, String mailTitle ){
        this.fileName = fileName;
        this.mailTitle = mailTitle;
    }

    public static EnEmailName searchByFileName( String fileName ){
        for(EnEmailName enEmailName : values()){
            if(enEmailName.getFileName().equals( fileName )){
                return enEmailName;
            }
        }
        return null;
    }

}
