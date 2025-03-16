//package com.swiftHearty.services;
//
//import com.swiftHearty.data.model.OTP;
//import com.swiftHearty.data.repository.OTPRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.TimerTask;
//
//
//public class ExpiredOTPCleanUp extends TimerTask {
//
//
////    private final OTPRepository otpRepository;
////    public ExpiredOTPCleanUp(OTPRepository otpRepository) {
////        this.otpRepository = otpRepository;
////    }
//
//    @Override
//    public void run() {
//        LocalDateTime currentTime = LocalDateTime.now();
//        List<OTP> expiredOTp = otpRepository.findOTPByExpirationTimeBefore(currentTime);
//
//        if(!expiredOTp.isEmpty()){
//            otpRepository.deleteAll(expiredOTp);
//        }
//    }
//}
