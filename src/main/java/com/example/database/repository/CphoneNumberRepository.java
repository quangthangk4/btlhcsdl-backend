package com.example.database.repository;

import com.example.database.entity.CphoneNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CphoneNumberRepository extends JpaRepository<CphoneNumberEntity,String> {
}
