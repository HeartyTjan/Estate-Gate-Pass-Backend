package com.swiftHearty.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNewTenantRequest {
    @Valid

    @NotNull(message = "firstName is required")
    @NotBlank(message = "firstName Cannot be blank")
    private String firstName;

    @NotNull(message = "lastName is required")
    @NotBlank(message = "lastName Cannot be blank")
    private String lastName;

    @NotNull(message = "email is required")
    @NotBlank(message = "email Cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "password is required")
    @NotBlank(message = "password Cannot be blank")
    @Size(min = 4, max = 15, message = "Password must not be below 5 and above 15")
    private String password;

    @NotNull(message = "phoneNumber is required")
    @NotBlank(message = "phoneNumber Cannot be blank")
    @Pattern(regexp = "^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$", message = "Invalid phone number")
    private String phoneNumber;

    @NotNull(message = "Apartment Id is required")
    @NotBlank(message = "Apartment Id Cannot be blank")
    private String apartmentId;

    private String role;

}
