package com.gsm.platfra.codes;

import lombok.Getter;

@Getter
public enum EnCommonMsg {

    UN_KNOW                     ("알 수 없는 오류가 발생하였습니다."),
    REGEST_MEMBER               ("회원가입이 완료 되었습니다."),
    ALREADY_MEMBER              ("이미 가입한 계정입니다."),
    SENt_MAIL                   ("인증 메일이 전송되었습니다");

    private String msg;

    EnCommonMsg( String msg ){
        this.msg = msg;
    }

    public static EnCommonMsg searchByMsg( String msg ){
        for( EnCommonMsg enCommonMsg : values() ){
            if( enCommonMsg.getMsg().equals( msg ) ){
                return enCommonMsg;
            }
        }
        return null;
    }

}
