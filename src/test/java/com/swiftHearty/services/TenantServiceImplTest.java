package com.swiftHearty.services;

import com.swiftHearty.Main;
import com.swiftHearty.data.repository.TenantRepository;
import com.swiftHearty.dto.request.CreateNewTenantRequest;
import com.swiftHearty.dto.request.UserLoginRequest;
import com.swiftHearty.dto.response.CreateNewUserResponse;
import com.swiftHearty.dto.response.UserLoginResponse;
import com.swiftHearty.exception.UserAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TenantServiceImplTest {

    @Autowired
    private TenantService tenantService;

    @BeforeEach
    public void setUp(){
        tenantService.clearTenants();

    }

    @Test
    public void createANewTenant_NewTenantCreated_CountIsOneTest(){
        CreateNewTenantRequest newTenantRequest = new CreateNewTenantRequest();
        newTenantRequest.setFirstName("First Name11");
        newTenantRequest.setLastName("Last Name");
        newTenantRequest.setEmail("email@gmail.com");
        newTenantRequest.setApartmentId("50");
        newTenantRequest.setPassword("Password");

        CreateNewUserResponse response = tenantService.registerNewTenant(newTenantRequest);
        assertEquals(1, tenantService.numberOfTenants());

    }

    @Test
    public void testSameTenantCannotBeCreatedTwice_throwsException(){
        CreateNewTenantRequest newTenantRequest = new CreateNewTenantRequest();
        newTenantRequest.setFirstName("First Name");
        newTenantRequest.setLastName("Last Name");
        newTenantRequest.setEmail("email@gmail.com");
        newTenantRequest.setApartmentId("50");
        newTenantRequest.setPassword("Password");

        tenantService.registerNewTenant(newTenantRequest);
        assertEquals(1, tenantService.numberOfTenants());
        assertThrows(UserAlreadyExistException.class,()-> tenantService.registerNewTenant(newTenantRequest));
    }

    @Test
    public void testTenantCreated_thenTenantLoginWithCorrectCredentials_returnLoginSuccessfullyAndTrue  (){
        CreateNewTenantRequest newTenantRequest = new CreateNewTenantRequest();
        newTenantRequest.setFirstName("First Name oo");
        newTenantRequest.setLastName("Last Name");
        newTenantRequest.setEmail("email@gmail.com");
        newTenantRequest.setApartmentId("50");
        newTenantRequest.setPassword("Password");

        tenantService.registerNewTenant(newTenantRequest);
        assertEquals(1, tenantService.numberOfTenants());
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmail("email@gmail.com");
        userLoginRequest.setPassword("Password");
        userLoginRequest.setRole("Tenant");
        UserLoginResponse loginResponse = tenantService.login(userLoginRequest);
        assertEquals("Login Successfully", loginResponse.getMessage());
        assertTrue(loginResponse.isSuccess());
    }

}