package com.swiftHearty.services;

import com.swiftHearty.data.model.OTP;
import com.swiftHearty.data.model.VisitorPass;
import com.swiftHearty.data.repository.OTPRepository;
import com.swiftHearty.dto.request.OTPRequest;
import com.swiftHearty.dto.request.ValidateOTPRequest;
import com.swiftHearty.dto.response.OTPResponse;
import com.swiftHearty.exception.ResourceNotFoundException;
import com.swiftHearty.utils.mapper.OTPMapper;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class OTPServiceImpl implements  OTPService{

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private VisitorPassService visitorPassService;



//    public OTPServiceImpl(){
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new ExpiredOTPCleanUp(otpRepository),0,60000);
//    }

    @Scheduled(fixedRate = 600000)
    @Override
    public void deleteExpiredOtp() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<OTP> expiredOTp = otpRepository.findOTPByExpirationTimeBefore(currentTime);

        if(!expiredOTp.isEmpty()){
            otpRepository.deleteAll(expiredOTp);
        }
    }
    @Override
    public String generateOTP(OTPRequest request) {
        GoogleAuthenticator authenticator = new GoogleAuthenticator();
        GoogleAuthenticatorKey key = authenticator.createCredentials();
        String otp = String.valueOf(authenticator.getTotpPassword(key.getKey()));


        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(10);
        OTP otpRecord =  createOTPRecord(request,otp,expirationTime);
        otpRepository.save(otpRecord);
        return otp;
    }

    @Override
    public OTPResponse retrieveOTPRecordByKey(ValidateOTPRequest request) {
        OTP foundOtp =  otpRepository.getOTPSByCode(request.getOtpCode())
                .orElseThrow(()-> new ResourceNotFoundException("Expired or Invalid OTP"));
        if (foundOtp.isUsed()) {
            throw new IllegalArgumentException("OTP Already Used");
        }

        boolean isOTPExpired = foundOtp.getExpirationTime().isBefore(LocalDateTime.now());
        if(isOTPExpired){
            throw new ResourceNotFoundException("Expired OTP");
        }
        foundOtp.setUsed(true);
        otpRepository.save(foundOtp);
        VisitorPass visitorPass =  visitorPassService.generateVisitorPass(foundOtp,request.getSecurityName());

        return OTPMapper.mapToResponse(foundOtp.getCode(),visitorPass);
    }

    @Override
    public Long numberOfOtps() {
        return otpRepository.count();
    }

    private OTP createOTPRecord(OTPRequest request,String OTP, LocalDateTime expirationTime){
        OTP otpRecord = new OTP();
        String fullName = String.join(" ", request.getFirstName(),request.getLastName());
        otpRecord.setUserFullName(fullName);
        otpRecord.setUsed(false);
        otpRecord.setCode(OTP);
        otpRecord.setExpirationTime(expirationTime);
        otpRecord.setUserPhoneNumber(request.getPhoneNumber());

        return otpRecord;
    }

//    public void triggerOTPCleanup() {
//        new ExpiredOTPCleanUp(otpRepository).run();
//    }

}