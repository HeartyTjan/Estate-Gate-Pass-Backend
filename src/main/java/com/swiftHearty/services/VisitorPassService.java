package com.swiftHearty.services;

import com.swiftHearty.data.model.OTP;
import com.swiftHearty.data.model.VisitorPass;
import com.swiftHearty.dto.response.VisitorPassResponse;

public interface VisitorPassService {
    VisitorPass generateVisitorPass(OTP otp, String securityName);
    VisitorPassResponse retrieveVisitorPass(String passId);

    VisitorPassResponse updatePass(String id);
}
