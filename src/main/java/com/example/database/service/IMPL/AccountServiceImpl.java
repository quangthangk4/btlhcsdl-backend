package com.example.database.service.IMPL;

import com.example.database.Model.dto.AccountDTO;
import com.example.database.Model.response.AccountResponse;
import com.example.database.converter.AccountConverrter;
import com.example.database.entity.AccountEntity;
import com.example.database.repository.AccountRepository;
import com.example.database.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public void save(AccountEntity accountEntity){
        accountRepository.save(accountEntity);
    }
    @Override
    public List<AccountResponse> ListGetAccountById(int id){
        List<AccountEntity> accounts = accountRepository.findByCustomerEntity_Cid(id);
        AccountConverrter accountConverter = new AccountConverrter();
        return accounts.stream()
                .map(account -> accountConverter.toAccountResponse(account))
                .collect(Collectors.toList());
    }
}
