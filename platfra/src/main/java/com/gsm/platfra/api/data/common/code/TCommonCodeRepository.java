package com.gsm.platfra.api.data.common.code;

import com.gsm.platfra.api.data.common.code.TCommonCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface TCommonCodeRepository extends JpaRepository<TCommonCode, String> {
    List<TCommonCode> findAllByParentCdAndTypeAndDelYn(String code, String type, Boolean delYn);
}