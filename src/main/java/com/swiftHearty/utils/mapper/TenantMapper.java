package com.swiftHearty.utils.mapper;

import com.swiftHearty.data.model.Tenant;
import com.swiftHearty.dto.request.CreateNewTenantRequest;
import com.swiftHearty.dto.response.CreateNewUserResponse;
import com.swiftHearty.dto.response.UserLoginResponse;

public class TenantMapper {

    public static Tenant mapRequestToTenant(CreateNewTenantRequest newUserRequest){
        Tenant newTenant = new Tenant();
        newTenant.setFirstName(newUserRequest.getFirstName());
        newTenant.setLastName(newUserRequest.getLastName());
        newTenant.setApartmentId(newUserRequest.getApartmentId());
        newTenant.setEmail(newUserRequest.getEmail());
        newTenant.setPhoneNumber(newUserRequest.getPhoneNumber());
       newTenant.setPassword(newUserRequest.getPassword());
        return newTenant;
    }

    public static CreateNewUserResponse mapToResponse(String message){
        CreateNewUserResponse newUserResponse = new CreateNewUserResponse();
        newUserResponse.setMessage(message);
        newUserResponse.setSuccess(true);
        return newUserResponse;
    }

    public static UserLoginResponse mapToLoginResponse(String message, Tenant tenant){
      UserLoginResponse loginResponse = new UserLoginResponse();
        loginResponse.setMessage(message);
        loginResponse.setFirstName(tenant.getFirstName());
        loginResponse.setLastName(tenant.getLastName());
        loginResponse.setPhoneNumber(tenant.getPhoneNumber());
        loginResponse.setSuccess(true);
        return loginResponse;
    }
}
