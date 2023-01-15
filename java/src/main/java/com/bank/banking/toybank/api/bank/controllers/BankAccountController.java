package com.bank.banking.toybank.api.bank.controllers;

import com.bank.banking.toybank.api.bank.dtos.RequestBankCreateDto;
import com.bank.banking.toybank.api.bank.services.BankAccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bank-account")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(final BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    public Long create(@RequestBody final RequestBankCreateDto requestBankCreateDto) {

        return bankAccountService.create(requestBankCreateDto);
    }
}
