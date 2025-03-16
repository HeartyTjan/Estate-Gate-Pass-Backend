package com.swiftHearty.utils.mapper;

import com.swiftHearty.data.model.OTP;
import com.swiftHearty.data.model.VisitorPass;
import com.swiftHearty.dto.response.OTPResponse;

import java.time.LocalDateTime;

public class OTPMapper {

    public static OTPResponse mapToResponse(String otp, VisitorPass visitorPass){
        OTPResponse response = new OTPResponse();
        response.setCode(otp);
        response.setVisitorPass(visitorPass);
        response.setSuccess(Boolean.TRUE);
        return response;
    }
}
