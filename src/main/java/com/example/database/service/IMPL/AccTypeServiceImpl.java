package com.example.database.service.IMPL;

import com.example.database.entity.AccTypeEntity;
import com.example.database.entity.AccountEntity;
import com.example.database.repository.AccTypeRepository;
import com.example.database.service.AccTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AccTypeServiceImpl implements AccTypeService {
    @Autowired
    private AccTypeRepository accTypeRepository;

    @Override
    public void save(AccTypeEntity accTypeEntity) {
        accTypeRepository.save(accTypeEntity);
    }
    @Override
    public AccTypeEntity findById(Integer id) {
        return accTypeRepository.findById(id).orElse(null);
    }
}
