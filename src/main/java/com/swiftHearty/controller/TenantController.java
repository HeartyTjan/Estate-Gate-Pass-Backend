package com.swiftHearty.controller;

import com.swiftHearty.dto.request.OTPRequest;
import com.swiftHearty.dto.response.OTPResponse;
import com.swiftHearty.services.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.client.HttpClientAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@CrossOrigin("*")
@RequestMapping("/tenant")
public class TenantController {

    @Autowired
    private OTPService otpService;
    @Autowired
    private HttpClientAutoConfiguration httpClientAutoConfiguration;

    @PostMapping("/generate")
    public ResponseEntity<OTPResponse> generateOtp(@RequestBody OTPRequest otpRequest) {
        String otp = otpService.generateOTP(otpRequest);
        if(otp != null) {
            OTPResponse response = new OTPResponse();
            response.setCode(otp);
            response.setSuccess(Boolean.TRUE);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
