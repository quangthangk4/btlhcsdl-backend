package com.example.database.Model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDTO {

    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String homeAddress;
    private String officeAddress;
}
