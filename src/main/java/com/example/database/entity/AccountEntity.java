package com.example.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="ACCOUNT")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="account_number")
    private Integer account_number;

    // Quan hệ ManyToOne với CustomerEntity

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "CID", nullable = false, referencedColumnName = "CID")
    private CustomerEntity customerEntity;

    // Quan hệ ManyToOne với AccTypeEntity
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ACCid", nullable = false, referencedColumnName = "ACCid")
    private AccTypeEntity accTypeEntity;
}
