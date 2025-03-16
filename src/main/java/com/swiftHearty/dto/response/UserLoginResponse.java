package com.swiftHearty.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResponse {
    private String message;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean isSuccess;
}
