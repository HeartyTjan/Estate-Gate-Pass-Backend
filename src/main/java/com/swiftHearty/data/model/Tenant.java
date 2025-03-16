package com.swiftHearty.data.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
@TypeAlias("tenant")
public class Tenant extends User{
    private String apartmentId;
}
