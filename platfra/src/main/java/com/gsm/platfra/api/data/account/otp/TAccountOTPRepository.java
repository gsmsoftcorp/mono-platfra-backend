package com.gsm.platfra.api.data.account.otp;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TAccountOTPRepository extends JpaRepository<TAccountOTP, Long> {
  Optional<TAccountOTP> findByUserId(String userId);
}
