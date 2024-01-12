package com.gsm.platfra.security.refresh;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findById(String refreshToken); // SELECT * FROM refresh_token WHERE id = ?1;
}
