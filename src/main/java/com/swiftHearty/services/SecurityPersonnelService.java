package com.swiftHearty.services;

import com.swiftHearty.dto.request.CreateNewSecurityPersonnelRequest;
import com.swiftHearty.dto.request.UserLoginRequest;
import com.swiftHearty.dto.response.CreateNewUserResponse;
import com.swiftHearty.dto.response.UserLoginResponse;

public interface SecurityPersonnelService {

    CreateNewUserResponse registerNewSecurityPersonnel(CreateNewSecurityPersonnelRequest newSecurityPersonnelRequest);

    Long numberOfSecurityPersonnels();

    void clearSecurityPersonnels();

    UserLoginResponse login(UserLoginRequest userLoginRequest);
}
