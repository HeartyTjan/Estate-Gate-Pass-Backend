package com.swiftHearty.services;

import com.swiftHearty.data.model.SecurityPersonnel;
import com.swiftHearty.data.model.User;
import com.swiftHearty.data.repository.SecurityPersonnelRepository;
import com.swiftHearty.dto.request.CreateNewSecurityPersonnelRequest;
import com.swiftHearty.dto.request.UserLoginRequest;
import com.swiftHearty.dto.response.CreateNewUserResponse;
import com.swiftHearty.dto.response.UserLoginResponse;
import com.swiftHearty.exception.ResourceNotFoundException;
import com.swiftHearty.exception.UserAlreadyExistException;
import com.swiftHearty.utils.mapper.SecurityPersonnelMapper;
import com.swiftHearty.utils.mapper.TenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityPersonnelImpl implements SecurityPersonnelService{

    @Autowired
    private SecurityPersonnelRepository securityPersonnelRepository;

    @Override
    public CreateNewUserResponse registerNewSecurityPersonnel(CreateNewSecurityPersonnelRequest newSecurityPersonnelRequest) {
        if(securityPersonnelExist(newSecurityPersonnelRequest.getEmail())){
            throw new UserAlreadyExistException("Security Personnel Already exist");
        }
        SecurityPersonnel securityPersonnel = SecurityPersonnelMapper.mapRequestToSecurity(newSecurityPersonnelRequest);
        securityPersonnelRepository.save(securityPersonnel);
        return TenantMapper.mapToResponse("Registration Successful");
    }

    @Override
    public Long numberOfSecurityPersonnels() {
        return securityPersonnelRepository.count();
    }

    @Override
    public void clearSecurityPersonnels() {
        securityPersonnelRepository.deleteAll();
    }

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        SecurityPersonnel securityPersonnel =  findSecurityPersonnelByEmail(userLoginRequest.getEmail());
        boolean isSuccessful = securityPersonnel.getPassword().equals(userLoginRequest.getPassword());
        if(!isSuccessful){
            throw  new IllegalArgumentException("Wrong Email or Password");
        }
        return SecurityPersonnelMapper.mapToLoginResponse("Login Successfully", securityPersonnel);

    }

    private SecurityPersonnel findSecurityPersonnelByEmail(String email){
        return securityPersonnelRepository.findSecurityPersonnelByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }

    private boolean securityPersonnelExist(String email){
        return securityPersonnelRepository.existsSecurityPersonnelByEmail(email);

    }
}
