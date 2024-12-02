package com.example.database.Model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class BranchResponse {
    private String bname;
    private String bemail;
    private Integer bno;
    private String bstreet;
    private String bdistrict;
    private String bcity;
    private String bregion;
    private List<String> bfaxNumbers;
    private List<String> bphoneNumbers;
}
