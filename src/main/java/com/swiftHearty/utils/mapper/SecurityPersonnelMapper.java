package com.swiftHearty.utils.mapper;

import com.swiftHearty.data.model.SecurityPersonnel;
import com.swiftHearty.dto.request.CreateNewSecurityPersonnelRequest;
import com.swiftHearty.dto.response.UserLoginResponse;


public class SecurityPersonnelMapper {
    
    public static SecurityPersonnel mapRequestToSecurity(CreateNewSecurityPersonnelRequest newSecurityPersonnelRequest) {
        SecurityPersonnel newSecurityPersonnel = new SecurityPersonnel();
        newSecurityPersonnel.setFirstName(newSecurityPersonnelRequest.getFirstName());
        newSecurityPersonnel.setLastName(newSecurityPersonnelRequest.getLastName());
        newSecurityPersonnel.setEmail(newSecurityPersonnelRequest.getEmail());
        newSecurityPersonnel.setPassword(newSecurityPersonnelRequest.getPassword());
        newSecurityPersonnel.setBadgeNumber(newSecurityPersonnelRequest.getBadgeNumber());
        return newSecurityPersonnel;
    }


    public static UserLoginResponse mapToLoginResponse(String message, SecurityPersonnel securityPersonnel) {
        UserLoginResponse loginResponse = new UserLoginResponse();
        loginResponse.setMessage(message);
        loginResponse.setFirstName(securityPersonnel.getFirstName());
        loginResponse.setLastName(securityPersonnel.getLastName());
        loginResponse.setPhoneNumber(securityPersonnel.getPhoneNumber());
        loginResponse.setSuccess(true);
        return loginResponse;
    }

}
