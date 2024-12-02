package com.example.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "BFAX_NUMBER")
public class BfaxNumberEntity {

    @Id
    @Column(name = "Bfax_number")
    private String Bfax_number;

    // Ánh xạ về BranchEntity (nếu là quan hệ Many-to-One)
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "Bname", referencedColumnName = "bname") // Bname tham chiếu đến bname của BranchEntity
    private BranchEntity branchEntity;
}
