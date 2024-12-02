package com.example.database.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "BRANCH")
public class BranchEntity {

    @Id
    @Column(name = "bname")
    private String bname;

    @Column(nullable = false, unique = true, name = "Bemail")
    private String bemail;

    @Column(nullable = false, name = "Bno")
    private Integer bno;

    // Ánh xạ đến BfaxNumberEntity
    @OneToMany(mappedBy = "branchEntity")     // "branchEntity" phải trùng với tên thuộc tính trong BfaxNumberEntity
    private List<BfaxNumberEntity> bfaxnumber;

    // Ánh xạ đến BphoneNumberEntity
    @OneToMany(mappedBy = "branchEntity") // "branchEntity" phải trùng với tên thuộc tính trong BphoneNumberEntity
    private List<BphoneNumberEntity> bphonenumber;

    @Column(name = "Bstreet")
    private String bstreet;

    @Column(name = "Bdistrict")
    private String bdistrict;

    @Column(name = "Bcity")
    private String bcity;

    @Column(name = "Bregion")
    private String bregion;

    // Ánh xạ đến EmployeeEntity
    @JsonManagedReference
    @OneToMany(mappedBy = "branch")
    private List<EmployeeEntity> employees;
}
