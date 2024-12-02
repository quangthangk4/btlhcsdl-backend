package com.example.database.service;

import com.example.database.entity.AccTypeEntity;

public interface AccTypeService {
    public AccTypeEntity findById(Integer id);
    void save(AccTypeEntity accTypeEntity);
}
