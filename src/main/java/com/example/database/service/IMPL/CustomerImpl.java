package com.example.database.service.IMPL;

import com.example.database.entity.CustomerEntity;
import com.example.database.repository.CustomerRepository;
import com.example.database.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<CustomerEntity> getCustomer(){
       return  customerRepository.findAll();
    }
    @Override
    public CustomerEntity findById(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }
}

