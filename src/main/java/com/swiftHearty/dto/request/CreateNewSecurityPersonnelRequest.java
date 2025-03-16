package com.swiftHearty.dto.request;

import lombok.Data;

@Data
public class CreateNewSecurityPersonnelRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String badgeNumber;
    private String role;
}
