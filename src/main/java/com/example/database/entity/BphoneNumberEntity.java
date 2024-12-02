package com.example.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "BPHONE_NUMBER")
public class BphoneNumberEntity {

    @Id
    @Column(name = "Bphone_number")
    private String bphoneNumber;

    // Ánh xạ về BranchEntity (nếu là quan hệ Many-to-One)
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "Bname", nullable = false, referencedColumnName = "bname") // Bname tham chiếu đến bname của BranchEntity
    private BranchEntity branchEntity;
}
