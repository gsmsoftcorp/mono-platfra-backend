package com.gsm.platfra.api.entity.account.repository;

import com.gsm.platfra.api.entity.account.TAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TAccountRepository extends JpaRepository<TAccount, Long> {
    Optional<TAccount> findByEmail(String email); // SELECT * FROM taccount WHERE email = ?1;
    Optional<TAccount> findById(Long id); // SELECT * FROM taccount WHERE id = ?1;
    List<TAccount> findByUuidIn(List<String> uuids); // SELECT * FROM taccount WHERE uuid IN (?1);
    Optional<TAccount> findByNickName(String nickName); // SELECT * FROM taccount WHERE nickName = ?1;
}

