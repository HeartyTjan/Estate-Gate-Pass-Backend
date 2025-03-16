package com.swiftHearty.dto.request;

import lombok.Data;

@Data
public class ValidateOTPRequest {
    private String otpCode;
    private String securityName;
}
