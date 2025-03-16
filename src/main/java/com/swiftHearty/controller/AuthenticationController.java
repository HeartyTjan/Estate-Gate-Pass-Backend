package com.swiftHearty.controller;

import com.swiftHearty.dto.request.CreateNewSecurityPersonnelRequest;
import com.swiftHearty.dto.request.CreateNewTenantRequest;
import com.swiftHearty.dto.request.UserLoginRequest;
import com.swiftHearty.dto.response.CreateNewUserResponse;
import com.swiftHearty.dto.response.UserLoginResponse;
import com.swiftHearty.services.SecurityPersonnelService;
import com.swiftHearty.services.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/register")
public class AuthenticationController {

    @Autowired
    private TenantService tenantService;

    @Autowired
    private SecurityPersonnelService securityPersonnelService;


    @PostMapping("/newTenant")
    public ResponseEntity<?> createNewTenant(@RequestBody CreateNewTenantRequest newTenantRequest) {
                CreateNewUserResponse response = tenantService.registerNewTenant(newTenantRequest);
                return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/newSecurity")
    public ResponseEntity<?> createNewSecurityPersonnel(@RequestBody CreateNewSecurityPersonnelRequest newSecurityPersonnelRequest) {
                CreateNewUserResponse response = securityPersonnelService.registerNewSecurityPersonnel(newSecurityPersonnelRequest);
                return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserLoginRequest userLoginRequest) {
        if(userLoginRequest.getRole() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        switch (userLoginRequest.getRole().toLowerCase()) {
            case "tenant" -> {
                return ResponseEntity.ok(tenantService.login(userLoginRequest));
            }
            case "security" -> {
                return ResponseEntity.ok(securityPersonnelService.login(userLoginRequest));
            }
            default -> {
                return ResponseEntity.badRequest().body("Invalid Request");
            }
        }
    }
}
