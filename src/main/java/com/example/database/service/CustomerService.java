package com.example.database.service;

import com.example.database.entity.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerEntity> getCustomer();
    CustomerEntity findById(Integer id);
}
