package com.bank.banking.toybank.domain.bank_account.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bank_account_number;

    private String bank_code;

    private String user_id;

    private BankAccount (Long id, String bank_account_number, String bank_code, String user_id) {
        this.id = id;
        this.bank_account_number = bank_account_number;
        this.bank_code = bank_code;
        this.user_id = user_id;
    }

    public BankAccount() {

    }
}
