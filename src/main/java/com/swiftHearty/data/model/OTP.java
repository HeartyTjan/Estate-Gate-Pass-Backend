package com.swiftHearty.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document
@Getter
@Setter
public class OTP {
    @Id
    private String id;
    private String code;
    private String userFullName;
    private String userPhoneNumber;
    private boolean isUsed;
    private LocalDateTime expirationTime;

}
