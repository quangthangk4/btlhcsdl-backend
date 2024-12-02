package com.example.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="CUSTOMER")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CID")
    private Integer cid;

    @Column(nullable = false, unique = true, name= "Cemail")
    private String cemail;

    @Column(nullable = false, name="Cfirst_name")
    private String cfirstName;

    @Column(nullable = false, name = "Clast_name")
    private String clastName;

    @Column (name = "Chome_address")
    private String chomeAddress;

    @Column (name="Coffice_address")
    private String cofficeAddress;


    @JsonManagedReference
    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountEntity> AcountEntitty;
    // Quan hệ OneToMany với CphoneNumberEntity
    @JsonManagedReference
    @OneToMany(mappedBy = "customerEntity") // "customerEntity" phải trùng với tên thuộc tính trong CphoneNumberEntity
    private List<CphoneNumberEntity> cphoneNumberEntitty;

    // Quan hệ ManyToOne với EmployeeEntity
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "EID", referencedColumnName="EID")
    private EmployeeEntity employee;
}
