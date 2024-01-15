package com.gsm.platfra.api.services.platfra.repository;

import com.gsm.platfra.api.entity.account.TAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<TAccount, String> {

    Optional<TAccount> findByUserId(String userId);

    Optional<Object> findByEmail(String email);
}
