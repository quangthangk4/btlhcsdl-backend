package com.example.database.Model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class AccountResponse {
    private Integer accountNumber;
    private String accountTypeName; // Loại tài khoản (tiết kiệm, vãng lai, vay, ...)
    private BigDecimal balance;     // Số dư tài khoản
    private String interestRate;
    private Date date;
}
