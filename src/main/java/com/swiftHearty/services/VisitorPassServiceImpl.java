package com.swiftHearty.services;

import com.swiftHearty.data.model.OTP;
import com.swiftHearty.data.model.VisitorPass;
import com.swiftHearty.data.repository.VisitorPassRepository;
import com.swiftHearty.dto.response.VisitorPassResponse;
import com.swiftHearty.exception.ResourceNotFoundException;
import com.swiftHearty.utils.mapper.VisitorPassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VisitorPassServiceImpl implements VisitorPassService{

    @Autowired
    private VisitorPassRepository visitorPassRepository;

    @Override
    public VisitorPass generateVisitorPass(OTP otp, String securityName ){
       VisitorPass visitorPass = VisitorPassMapper.mapToPass(otp,securityName);
       visitorPassRepository.save(visitorPass);
       return visitorPass;
    }

    @Override
    public VisitorPassResponse retrieveVisitorPass(String passId) {
       VisitorPass foundPass = findPassById(passId);
       return VisitorPassMapper.mapToResponse(foundPass);
    }

    private VisitorPass findPassById(String id){
        return visitorPassRepository.findVisitorPassById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Visitor Pass not Found or Invalid Code"));
    }

    @Override
    public VisitorPassResponse updatePass(String id) {
        VisitorPass visitorPass = findPassById(id);
        if(visitorPass.getTimeOut() != null){
            throw new IllegalArgumentException("Exited already");
        }
        visitorPass.setOpen(false);
        visitorPass.setTimeOut(LocalDateTime.now());
        visitorPassRepository.save(visitorPass);
        return  VisitorPassMapper.mapToResponse(visitorPass);
    }
}
