package com.bank.banking.toybank.api.bank.services;

import com.bank.banking.toybank.api.bank.dtos.RequestBankCreateDto;
import com.bank.banking.toybank.domain.bank_account.services.BankAccountDomainService;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    private final BankAccountDomainService bankAcountDomainService;

    public BankAccountService(final BankAccountDomainService bankAccountDomainService) {
        this.bankAcountDomainService = bankAccountDomainService;
    }

    public Long create(RequestBankCreateDto requestBankCreateDto) {
        return bankAcountDomainService.create(requestBankCreateDto);
    }
}
