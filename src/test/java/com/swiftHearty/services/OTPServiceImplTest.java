package com.swiftHearty.services;

import com.swiftHearty.data.model.OTP;
import com.swiftHearty.data.repository.OTPRepository;
import com.swiftHearty.dto.request.CreateNewTenantRequest;
import com.swiftHearty.dto.request.OTPRequest;
import com.swiftHearty.dto.request.UserLoginRequest;
import com.swiftHearty.dto.request.ValidateOTPRequest;
import com.swiftHearty.dto.response.OTPResponse;
import com.swiftHearty.dto.response.UserLoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OTPServiceImplTest {

    @Autowired
    private TenantService tenantService;

    @Autowired
    private OTPService otpService;

    @Autowired
    private OTPRepository otpRepository;

    @BeforeEach
    public void setUp(){
        tenantService.clearTenants();
        CreateNewTenantRequest newTenantRequest = new CreateNewTenantRequest();
        newTenantRequest.setFirstName("First Name");
        newTenantRequest.setLastName("Last Name");
        newTenantRequest.setPhoneNumber("phoneNumber");
        newTenantRequest.setEmail("email@gmail.com");
        newTenantRequest.setApartmentId("50");
        newTenantRequest.setPassword("Password");

        tenantService.registerNewTenant(newTenantRequest);
        assertEquals(1, tenantService.numberOfTenants());
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setEmail("email@gmail.com");
        userLoginRequest.setPassword("Password");
        UserLoginResponse loginResponse = tenantService.login(userLoginRequest);
        assertEquals("Login Successfully", loginResponse.getMessage());
        assertTrue(loginResponse.isSuccess());

        otpRepository.deleteAll();

    }

    @Test
    public void testCreatedUserCanGenerateOTP_andSaveWithUserDetails(){
        OTPRequest request = new OTPRequest();
        request.setFirstName("firstname");
        request.setLastName("lastname");
        request.setPhoneNumber("phone number");

        String otp = otpService.generateOTP(request);
        assertEquals(1, otpService.numberOfOtps());

        ValidateOTPRequest request1 = new ValidateOTPRequest();
        request1.setOtpCode(otp);
        OTPResponse savedOtpRecord = otpService.retrieveOTPRecordByKey(request1);
        String fullName = String.join(" ", request.getFirstName(),request.getLastName());
        assertEquals(fullName, savedOtpRecord.getVisitorPass().getOtp().getUserFullName());
        assertEquals(request.getPhoneNumber(), savedOtpRecord.getVisitorPass().getOtp().getUserPhoneNumber());

    }

    @Test
    public void testThatGeneratedOTP_willBeDeletedOnceStipulatedTimeElapsed() throws InterruptedException {
        OTPRequest request = new OTPRequest();
        request.setFirstName("firstname");
        request.setLastName("lastname");
        request.setPhoneNumber("phone number");

        String otp = otpService.generateOTP(request);
        assertEquals(1, otpService.numberOfOtps());

        OTPRequest anotherRequest = new OTPRequest();
        request.setFirstName("firstname1");
        request.setLastName("lastname1");
        request.setPhoneNumber("phone number1");

        String anotherOtp = otpService.generateOTP(request);
        assertEquals(2, otpService.numberOfOtps());

        Thread.sleep(65000);

        otpService.deleteExpiredOtp();

        assertEquals(0, otpService.numberOfOtps());

    }
}