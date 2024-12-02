package com.example.database.Model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
public class AccountDTO{
private Integer customerId; // CID của khách hàng
private Integer accid;  // ACCid của loại tài khoản

// Thông tin cho AccTypeEntity nếu cần cập nhật
@JsonProperty("sFlag")
private Boolean sFlag;             // Đánh dấu tài khoản tiết kiệm
private BigDecimal sbalance;       // Số dư tài khoản tiết kiệm
private BigDecimal sinterestRate;  // Lãi suất tài khoản tiết kiệm
private Date sdateOpened;          // Ngày mở tài khoản tiết


@JsonProperty("cFlag")
private Boolean cFlag;             // Đánh dấu tài khoản vãng lai
private BigDecimal cbalance;       // Số dư tài khoản vãng lai
private Date cdateOpened;          // Ngày mở tài khoản vãng lai

@JsonProperty("lFlag")
private Boolean lFlag;             // Đánh dấu tài khoản vay
private Date ldateTaken;           // Ngày vay
private BigDecimal linterestRate;  // Lãi suất vay
private BigDecimal lbalanceDue;
}