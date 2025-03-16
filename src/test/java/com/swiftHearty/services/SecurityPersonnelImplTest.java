package com.swiftHearty.services;


import com.swiftHearty.dto.request.CreateNewSecurityPersonnelRequest;
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
class SecurityPersonnelImplTest {

    @Autowired
    private SecurityPersonnelService securityPersonnel;

    @BeforeEach
    public void setUp(){
        securityPersonnel.clearSecurityPersonnels();

    }

    @Test
    public void createANewSecurityPersonnel_NewSecurityPersonnelCreated_CountIsOneTest(){
        CreateNewSecurityPersonnelRequest newRequest = new CreateNewSecurityPersonnelRequest();
        newRequest.setFirstName("First Name should be in tenant");
        newRequest.setLastName("Last Name");
        newRequest.setEmail("email@gmail.com");
        newRequest.setBadgeNumber("50");
        newRequest.setPassword("Password");
        newRequest.setRole("tenant");

        CreateNewUserResponse response = securityPersonnel.registerNewSecurityPersonnel(newRequest);
        assertEquals(1, securityPersonnel.numberOfSecurityPersonnels());

    }

    @Test
    public void testSameSecurityPersonnelCannotBeCreatedTwice_throwsException(){
        CreateNewSecurityPersonnelRequest newRequest = new CreateNewSecurityPersonnelRequest();
        newRequest.setFirstName("First Name");
        newRequest.setLastName("Last Name");
        newRequest.setEmail("email@gmail.com");
        newRequest.setBadgeNumber("50");
        newRequest.setPassword("Password");

        securityPersonnel. registerNewSecurityPersonnel(newRequest);
        assertEquals(1, securityPersonnel.numberOfSecurityPersonnels());
        assertThrows(UserAlreadyExistException.class,()-> securityPersonnel. registerNewSecurityPersonnel(newRequest));
    }

    @Test
    public void testSecurityPersonnelCreated_thenLoginWithCorrectCredentials_returnLoginSuccessfullyAndTrue  (){
        CreateNewSecurityPersonnelRequest newRequest = new CreateNewSecurityPersonnelRequest();
        newRequest.setFirstName("First Name11");
        newRequest.setLastName("Last Name");
        newRequest.setEmail("email@gmail.com");
        newRequest.setBadgeNumber("50");
        newRequest.setPassword("Password");

        securityPersonnel. registerNewSecurityPersonnel(newRequest);
        assertEquals(1, securityPersonnel.numberOfSecurityPersonnels());
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmail("email@gmail.com");
        userLoginRequest.setPassword("Password");
        userLoginRequest.setRole("SecurityPersonnel");
        UserLoginResponse loginResponse = securityPersonnel.login(userLoginRequest);
        assertEquals("Login Successfully", loginResponse.getMessage());
        assertTrue(loginResponse.isSuccess());
    }

}