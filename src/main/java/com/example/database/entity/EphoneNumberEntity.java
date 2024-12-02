package com.example.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "EPHONE_NUMBER")
public class EphoneNumberEntity {

    @Id
    @Column(name = "Ephone_number")
    private String ephoneNumber;

    // Quan hệ ManyToOne với EmployeeEntity
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "EID", nullable = false, referencedColumnName = "EID")
    private EmployeeEntity employeeEntity;
}
