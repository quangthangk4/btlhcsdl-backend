package com.example.database.repository;

import com.example.database.entity.BphoneNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BphoneNumberRepository extends JpaRepository<BphoneNumberEntity,String> {
}
