package com.swiftHearty.services;

import com.swiftHearty.data.model.OTP;
import com.swiftHearty.data.model.User;
import com.swiftHearty.dto.request.OTPRequest;
import com.swiftHearty.dto.request.ValidateOTPRequest;
import com.swiftHearty.dto.response.OTPResponse;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public interface OTPService {
    String generateOTP(OTPRequest request);
    OTPResponse retrieveOTPRecordByKey(ValidateOTPRequest request);
    Long numberOfOtps();
    void deleteExpiredOtp();
}
