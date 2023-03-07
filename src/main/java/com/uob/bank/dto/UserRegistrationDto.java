package com.uob.bank.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int contactNumber;
    private String address;
    private String nomineeDetails;

    public UserRegistrationDto(
            String firstName,
            String lastName,
            String email,
            String password,
            int contactNumber,
            String address,
            String nomineeDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.address = address;
        this.nomineeDetails = nomineeDetails;
    }

    public UserRegistrationDto(
            String firstName, String lastName, int contactNumber, String address, String nomineeDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.nomineeDetails = nomineeDetails;
    }
}
