package com.swiftHearty.dto.request;

import lombok.Data;

@Data
public class OTPRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
