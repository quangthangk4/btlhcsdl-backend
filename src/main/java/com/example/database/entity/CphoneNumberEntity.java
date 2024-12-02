package com.example.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "CPHONE_NUMBER")
public class CphoneNumberEntity {

    @Id
    @Column(name = "Cphone_number")
    private String cphoneNumber;

    // Quan hệ ManyToOne với CustomerEntity
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "CID", nullable = false, referencedColumnName = "CID")
    private CustomerEntity customerEntity;
}
