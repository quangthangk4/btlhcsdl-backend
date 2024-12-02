package com.example.database.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="ACC_TYPE")
public class AccTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCid")
    private Integer accid;

    @Column(name = "SFlag")
    private Boolean sFlag;

    @Column(name="Sbalance")
    private BigDecimal sbalance;

    @Column (name = "Sinterest_rate")
    private BigDecimal sinterestRate;

    @Column (name = "Sdate_opened")
    private Date sdateOpened;

    @Column (name = "CFlag" )
    private Boolean cFlag;

    @Column (name = "Cbalance")
    private BigDecimal cbalance;

    @Column (name = "Cdate_opened")
    private Date cdateOpened;

    @Column (name = "LFlag")
    private Boolean lFlag;

    @Column (name = "Ldate_taken")
    private Date ldateTaken;

    @Column (name = "Linterest_rate")
    private BigDecimal linterestRate;

    @Column (name = "Lbalance")
    private BigDecimal lbalanceDue;

    public AccTypeEntity() {
        this.sFlag = false;
        this.cFlag = false;
        this.lFlag = false;
    }

    // Quan hệ OneToMany với AccountEntity
    @JsonManagedReference
    @OneToMany(mappedBy = "accTypeEntity")  // "accTypeEntity" phải trùng với tên thuộc tính trong AccountEntity
    private List<AccountEntity> accList;
}
