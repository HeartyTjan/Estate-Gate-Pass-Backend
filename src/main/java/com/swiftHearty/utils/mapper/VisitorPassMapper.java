package com.swiftHearty.utils.mapper;

import com.swiftHearty.data.model.OTP;
import com.swiftHearty.data.model.VisitorPass;
import com.swiftHearty.dto.response.VisitorPassResponse;

import java.time.LocalDateTime;

public class VisitorPassMapper {

    public static VisitorPass mapToPass(OTP otp,String securityName){
        VisitorPass visitorPass = new VisitorPass();
        visitorPass.setOtp(otp);
        visitorPass.setTimeIn(LocalDateTime.now());
        visitorPass.setOpen(true);
        visitorPass.setSecurityName(securityName);
        return visitorPass;
    }

    public static VisitorPassResponse mapToResponse(VisitorPass pass){
        VisitorPassResponse response = new VisitorPassResponse();
        response.setVisitorPass(pass);
        response.setSuccess(true);
        return response;
    }
}
