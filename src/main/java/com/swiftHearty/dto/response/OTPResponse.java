package com.swiftHearty.dto.response;

import com.swiftHearty.data.model.VisitorPass;
import lombok.Data;

@Data
public class OTPResponse {
    private String code;
    private boolean success;
    private VisitorPass visitorPass;
}
