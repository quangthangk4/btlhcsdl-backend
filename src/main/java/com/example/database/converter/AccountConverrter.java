package com.example.database.converter;

import com.example.database.Model.dto.AccountDTO;
import com.example.database.Model.response.AccountResponse;
import com.example.database.entity.AccTypeEntity;
import com.example.database.entity.AccountEntity;
import com.example.database.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountConverrter {

    public static AccountEntity toEntity(AccountDTO dto, CustomerEntity customer, AccTypeEntity accType) {
        AccountEntity accountEntity = new AccountEntity();

        // Liên kết với khách hàng
        accountEntity.setCustomerEntity(customer);

        // Liên kết với loại tài khoản
        accountEntity.setAccTypeEntity(accType);

        return accountEntity;
    }
    public static AccTypeEntity createAccType(AccountDTO dto, AccTypeEntity accTypeEntity) {
        if (dto.getSFlag()) {
            accTypeEntity.setSFlag(true);
            accTypeEntity.setSbalance(dto.getSbalance());
            accTypeEntity.setSinterestRate(dto.getSinterestRate());
            accTypeEntity.setSdateOpened(dto.getSdateOpened());
        }

        if (dto.getCFlag()) {
            accTypeEntity.setCFlag(true);
            accTypeEntity.setCbalance(dto.getCbalance());
            accTypeEntity.setCdateOpened(dto.getCdateOpened());
        }

        if (dto.getLFlag()) {
            accTypeEntity.setLFlag(true);
            accTypeEntity.setLdateTaken(dto.getLdateTaken());
            accTypeEntity.setLinterestRate(dto.getLinterestRate());
            accTypeEntity.setLbalanceDue(dto.getLbalanceDue());
        }

        return accTypeEntity;
    }
    public static AccountResponse toAccountResponse(AccountEntity accountEntity) {
        AccountResponse dto = new AccountResponse();

        dto.setAccountNumber(accountEntity.getAccount_number());
        dto.setAccountTypeName(accountEntity.getAccTypeEntity().getSFlag() ? "Savings" :
                accountEntity.getAccTypeEntity().getCFlag() ? "Checking" :
                        accountEntity.getAccTypeEntity().getLFlag() ? "Loan" : "Unknown");

        if (accountEntity.getAccTypeEntity().getSFlag()) {
            dto.setBalance(accountEntity.getAccTypeEntity().getSbalance());
            dto.setInterestRate(accountEntity.getAccTypeEntity().getSinterestRate().toString());
            dto.setDate(accountEntity.getAccTypeEntity().getSdateOpened());
        } else if (accountEntity.getAccTypeEntity().getCFlag()) {
            dto.setBalance(accountEntity.getAccTypeEntity().getCbalance());
            dto.setInterestRate("N/A");
            dto.setDate(accountEntity.getAccTypeEntity().getCdateOpened());
        } else if (accountEntity.getAccTypeEntity().getLFlag()) {
            dto.setBalance(accountEntity.getAccTypeEntity().getLbalanceDue());
            dto.setInterestRate(accountEntity.getAccTypeEntity().getLinterestRate().toString());
            dto.setDate(accountEntity.getAccTypeEntity().getLdateTaken());
        }

        return dto;
    }
}
