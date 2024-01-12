package com.gsm.platfra.api.entity.account.utils;
import com.gsm.platfra.api.entity.account.TAccount;

import java.util.List;

/*
  상태 코드에 대한 설명 참조: https://www.rfc-editor.org/rfc/rfc7231
 */
public interface TAccountVerificationManager {
    /**
     * <h2>email 을 이용해서 회원을 검색하는 메서드</h2>
     * email 로 회원 검색 후 검증하여 리턴해준다.
     * @param email
     * @return 이메일과 일치하는 tAccount
     */
    TAccount get(String email);

    /**
     * <h2>id 을 이용해서 회원을 검색하는 메서드</h2>
     * email 로 회원 검색 후 검증하여 리턴해준다.
     * @param id
     * @return id와 일치하는 tAccount
     */
    TAccount getById(Long id);

    /**
     * <h2> uuid를 이용해서 회원을 검색하는 메서드</h2>
     * uuid 리스트로 회원 검색 후 검증하여 리턴해준다.
     * @param uuids
     * @return uuid 리스트와 일치하는 tAccount 리스트
     */
    List<TAccount> getList(List<String> uuids);

    /**
     * <h2> 현재 활동 가능한 회원인지 확인하는 메서드</h2>
     * 회원정보(TAccount)를 입력받아 현재 활동중인지 확인
     * 삭제, 휴먼 계정이면 오류를 throw
     * @param tAccount
     */
    void checkIsActive(TAccount tAccount);

    /**
     * <h2> 현재 활동 가능한 회원인지 확인하는 메서드</h2>
     * 회원정보(TAccount)를 입력받아 현재 활동중인지 확인
     * 삭제, 휴먼 계정이면 false를 리턴
     * @param tAccount
     * @return boolean
     */
    boolean isActive(TAccount tAccount);

    /**
     * <h2>이미 존재하는 이메일인지에 대한 검증</h2>
     * 중복 불가 unique, update 불가 속성인 email을 이용하여, 이미 존재하는 이메일인지를 검증<br>
     * <font color=white>409 Conflict </font>
     * @param email
     */
    void checkEmailExists(String email);

    /**
     * <h2>이미 존재하는 닉네임인지에 대한 검증</h2>
     * 중복 불가 unique 속성인 nickName을 이용하여, 이미 존재하는 nickName인지를 검증<br>
     * <font color=white>409 Conflict </font>
     * @param nickName
     */
    void checkNicknameExists(String nickName);

    /**
     * <h2>현재 로그인 된 사용자의 정보를 리턴해주는 메서드</h2>
     * security context에서 인증 정보를 받아 해당 사용자의 tAccount 객체를 리턴<br>
     * {@link #get(String)} 으로도 사용할 수 있지만, 명시적으로 개별로 정의<br>
     * @param email
     * @return 접속 중인 TAccount 의 정보
     */
    TAccount getLoggedIn(String email);
}
