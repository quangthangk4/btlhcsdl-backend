package com.example.database.repository;

import com.example.database.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Integer> {
    List<AccountEntity> findByCustomerEntity_Cid(Integer cid);
}
