package com.example.database.service;

import com.example.database.Model.dto.AccountDTO;
import com.example.database.Model.response.AccountResponse;
import com.example.database.entity.AccountEntity;

import java.util.List;

public interface AccountService {
    List<AccountResponse> ListGetAccountById(int id);
    void save(AccountEntity accountEntity);

}
