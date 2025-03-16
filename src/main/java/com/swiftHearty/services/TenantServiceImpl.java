package com.swiftHearty.services;

import com.swiftHearty.data.model.Tenant;
import com.swiftHearty.data.repository.TenantRepository;
import com.swiftHearty.dto.request.CreateNewTenantRequest;
import com.swiftHearty.dto.request.UserLoginRequest;
import com.swiftHearty.dto.response.CreateNewUserResponse;
import com.swiftHearty.dto.response.UserLoginResponse;
import com.swiftHearty.exception.ResourceNotFoundException;
import com.swiftHearty.exception.UserAlreadyExistException;
import com.swiftHearty.utils.mapper.TenantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImpl implements  TenantService{

    @Autowired
    private TenantRepository tenantRepository;

    @Override
    public CreateNewUserResponse registerNewTenant (CreateNewTenantRequest newTenantRequest) {
        if(tenantExist(newTenantRequest.getEmail())){
            throw new UserAlreadyExistException("Tenant Already exist");
        }
        Tenant createdTenant = TenantMapper.mapRequestToTenant(newTenantRequest);
        tenantRepository.save(createdTenant);
        return TenantMapper.mapToResponse("Registration Successful");
    }

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        Tenant tenant =  findUserByEmail(userLoginRequest.getEmail());
        boolean isSuccessful = tenant.getPassword().equals(userLoginRequest.getPassword());
        if(!isSuccessful){
            throw  new IllegalArgumentException("Wrong Email or Password");
        }
        return TenantMapper.mapToLoginResponse("Login Successfully",tenant);

    }

    @Override
    public Long numberOfTenants() {
        return tenantRepository.count();
    }

    @Override
    public void clearTenants() {
        tenantRepository.deleteAll();
    }

    private boolean tenantExist(String email){
        return tenantRepository.existsTenantByEmail(email);

    }
    private Tenant findUserByEmail(String email){
        return tenantRepository.findTenantByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }
}
