package com.example.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "EMPLOYEE")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EID")
    private Integer eid;

    @Column(nullable = false, unique = true, name = "Eemail")
    private String eemail;

    // Quan hệ OneToMany với EphoneNumberEntity
    @JsonManagedReference
    @OneToMany(mappedBy = "employeeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EphoneNumberEntity> ephoneNumber;

    @Column(nullable = false, name = "Edob")
    private Date edob;

    @Column(nullable = false, name = "Efirst_name")
    private String efirstName;

    @Column(nullable = false, name = "Elast_name")
    private String elastName;

    @Column(nullable = false, name = "Eno")
    private Integer eno;

    @Column(name = "Estreet")
    private String estreet;

    @Column(name = "Edistrict")
    private String edistrict;

    @Column(name = "Ecity")
    private String ecity;

    // Quan hệ ManyToOne với BranchEntity
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "Bname", nullable = false, referencedColumnName = "bname")
    private BranchEntity branch;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerEntity> customers;
}
