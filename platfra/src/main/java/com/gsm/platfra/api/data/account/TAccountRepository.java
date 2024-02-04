package com.gsm.platfra.api.data.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TAccountRepository extends JpaRepository<TAccount, String> {

    Optional<TAccount> findByUserId(String userId);

    Optional<TAccount> findByEmail(String email);

    Optional<TAccount> findByUserNm(String username);
}
