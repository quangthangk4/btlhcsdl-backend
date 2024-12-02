package com.example.database.repository;

import com.example.database.entity.AccTypeEntity;
import com.example.database.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccTypeRepository extends JpaRepository<AccTypeEntity,Integer> {
}
