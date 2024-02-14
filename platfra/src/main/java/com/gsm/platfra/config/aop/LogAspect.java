package com.gsm.platfra.config.aop;

import com.gsm.platfra.api.common.error.service.CommonErrorService;
import com.gsm.platfra.api.data.common.error.CommonErrorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class LogAspect {
    private final CommonErrorService commonErrorService;

    // Controller 내의 모든 메서드에 적용될 Pointcut을 정의
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
        // 이 메서드는 Pointcut을 정의하기 위한 것이므로 구현은 필요 없습니다.
    }
    // @ExceptionHandler 메서드를 대상으로 하는 Pointcut 정의
    @Pointcut("within(@org.springframework.web.bind.annotation.RestControllerAdvice *)")
    public void exception() {
    }

    @Around("controller()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 디버그 모드일 때 메서드 진입 로그 출력
        String url = "";
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            // 요청 URL 로깅
            url = attributes.getRequest().getRequestURL().toString();
//            url = attributes.getRequest().getRequestURI();
        }
        // 디버그 모드일 때 메서드 진입 로그 출력
        if (log.isDebugEnabled()) {
            log.debug("진입: {}.{}()", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName());
            log.debug("인수 = {}", Arrays.toString(joinPoint.getArgs()));
        }
        try {
            // 실제 메서드 실행
            Object result = joinPoint.proceed();

            // 디버그 모드일 때 메서드 종료 로그 출력
            if (log.isDebugEnabled()) {
                log.debug("종료: {}.{}()", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName());
                log.debug("결과 = {}", result);
            }
            return result;

        }  catch (Exception e){
            log.error("잘못된 인수: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

            String location = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "()";

            CommonErrorDto commonErrorDto = CommonErrorDto.builder()
                    .url(url)
                    .location(location)
                    .reqData(Arrays.toString(joinPoint.getArgs()))
                    .errorMsg(e.getMessage())
                    .build();

            log.error("Exception : Error Log Save");
            commonErrorService.errorLogging(commonErrorDto);

            throw e;
        }
    }

    @Around("exception()")
    public Object exceptionLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // TODO : 앞서 저장한 Error Log Id를 어떻게 가져와서 업데이트 쳐줘야할지? 응답 데이터 저장하기가 번거로움.
        // 일단 요청 관련 정보, 에러 메세지는 저장해두고 응답 데이터 같은 경우는 로그만 찍도록 함.
        log.error("Exception : Exception Handler Enter");

        Object result = joinPoint.proceed();

        log.error("Response Data : {}", result.toString());
        log.error("Exception : Response Data Update");
        commonErrorService.errLogUpdate(result);
        return result;
    }
}