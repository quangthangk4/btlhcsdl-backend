package com.example.database.service.IMPL;

import com.example.database.Model.response.BranchResponse;
import com.example.database.converter.BranchConverter;
import com.example.database.entity.BranchEntity;
import com.example.database.repository.BranchRepository;
import com.example.database.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    BranchConverter branchConverter;
    @Override
    public List<BranchResponse> getAllBranch() {
        List<BranchEntity> branchEntities = branchRepository.findAll();
        List<BranchResponse> res=new ArrayList<>();
        for(BranchEntity branchEntity : branchEntities){
            res.add(branchConverter.toDto(branchEntity));
        }
        return res;
    }
}
