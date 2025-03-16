package com.swiftHearty.controller;

import com.swiftHearty.dto.request.ValidateOTPRequest;
import com.swiftHearty.dto.response.OTPResponse;
import com.swiftHearty.dto.response.VisitorPassResponse;
import com.swiftHearty.services.OTPService;
import com.swiftHearty.services.VisitorPassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/security")
@RestController
@CrossOrigin("*")
public class SecurityController {

    @Autowired
    private OTPService otpService;

    @Autowired
    private VisitorPassService visitorPassService;

    @PostMapping("/validate")
    public ResponseEntity<OTPResponse> validateOtp(@RequestBody ValidateOTPRequest request){
      OTPResponse response =  otpService.retrieveOTPRecordByKey(request);
      return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<VisitorPassResponse> retrievePass(@PathVariable("id") String id){
        VisitorPassResponse response =  visitorPassService.retrieveVisitorPass(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<VisitorPassResponse> updatePass(@PathVariable("id") String id){
       VisitorPassResponse response = visitorPassService.updatePass(id);
       return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
