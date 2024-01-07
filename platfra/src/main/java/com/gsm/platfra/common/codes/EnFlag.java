package com.gsm.platfra.common.codes;

import lombok.Getter;

// 통신등 성공 여부 비교용
@Getter
public enum EnFlag {

    SUCCESS(1 , true),
    FAIL(0, false);

    private int typeInt;
    private Boolean typeBool;

    EnFlag( int typeInt, boolean typeBool ){
        this.typeInt = typeInt;
        this.typeBool = typeBool;
    }

    public static EnFlag searchByTypeInt( int typeInt ){
        for(EnFlag enFlag : values()){
            if(enFlag.getTypeInt() == typeInt){
                return enFlag;
            }
        }
        return null;
    }

    public static EnFlag searchByTypeBool(boolean typeBool){
        for(EnFlag enFlag : values()){
            if(enFlag.getTypeBool() == typeBool){
                return enFlag;
            }
        }
        return null;
    }
}
