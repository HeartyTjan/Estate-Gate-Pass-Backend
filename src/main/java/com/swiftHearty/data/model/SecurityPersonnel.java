package com.swiftHearty.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@Document
@TypeAlias("security")
public class SecurityPersonnel extends User {
    private String badgeNumber;
}
