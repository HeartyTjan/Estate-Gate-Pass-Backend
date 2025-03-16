package com.swiftHearty.services;

import com.swiftHearty.dto.request.CreateNewTenantRequest;
import com.swiftHearty.dto.request.UserLoginRequest;
import com.swiftHearty.dto.response.CreateNewUserResponse;
import com.swiftHearty.dto.response.UserLoginResponse;

public interface TenantService {
    Long numberOfTenants();

    void clearTenants();

    CreateNewUserResponse registerNewTenant(CreateNewTenantRequest newUserRequest);

    UserLoginResponse login(UserLoginRequest userLoginRequest);


}
