package com.gsm.platfra.api.data.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private T data;        //리턴 데이터
    private String code;        //업무처리결과 코드(httpStatus 코드 아님)
    private String message;     //기타 참고 메세지 (임의의 중요 메세지, 업무처리 결과에 따른 메세지, 에러메세지는 code 필드 활용하여 프론트에서 해결)
    private Exception error;    //예외, 에러 stack trace
}
