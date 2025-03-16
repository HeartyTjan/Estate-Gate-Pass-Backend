package com.swiftHearty.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNewTenantRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String apartmentId;
    private String password;
    private String phoneNumber;
    private String role;

}
