package com.example.database.repository;

import com.example.database.entity.EphoneNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EphoneNumberRepository extends JpaRepository<EphoneNumberEntity,String> {
    List<EphoneNumberEntity> findByEmployeeEntity_Eid(Integer eid);
}
