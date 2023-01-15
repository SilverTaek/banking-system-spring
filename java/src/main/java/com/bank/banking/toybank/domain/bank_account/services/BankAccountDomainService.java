package com.bank.banking.toybank.domain.bank_account.services;

import com.bank.banking.toybank.api.bank.dtos.RequestBankCreateDto;
import com.bank.banking.toybank.domain.bank_account.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class BankAccountDomainService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountDomainService(final BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public Long create(RequestBankCreateDto requestBankCreateDto) {
        return bankAccountRepository.save(requestBankCreateDto.toBankAccount()).getId();
    }
}
