package com.gsm.platfra.api.services.account.repository;

import com.gsm.platfra.api.entity.account.TAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<TAccount, String> {

    Optional<TAccount> findByUserId(String userId);

    Optional<TAccount> findByEmail(String email);

    Optional<TAccount> findByUserNm(String username);
}
