package com.example.database.Model.response;

import com.example.database.Model.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ReportResponse {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private List<String> phoneNumber;
    private String street;
    private String district;
    private String city;
    private List<CustomerDTO> customers;
}
