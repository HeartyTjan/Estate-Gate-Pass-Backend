package com.swiftHearty.data.repository;

import com.swiftHearty.data.model.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OTPRepository extends MongoRepository<OTP,String> {
    Optional<OTP>  getOTPSByCode(String otp);

  List<OTP> findOTPByExpirationTimeBefore(LocalDateTime currentTime);
}
