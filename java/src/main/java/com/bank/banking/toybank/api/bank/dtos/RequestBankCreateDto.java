package com.bank.banking.toybank.api.bank.dtos;

import com.bank.banking.toybank.domain.bank_account.entities.BankAccount;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RequestBankCreateDto {

    @NotNull(message = "계좌 번호를 입력해주세요.")
    private String bank_account_number;

    @NotNull(message = "은행 코드를 입력해주세요.")
    private String bank_code;

    @NotNull(message = "사용자 ID를 입력해주세요.")
    private String user_id;

    public BankAccount toBankAccount() {
        return BankAccount.builder().bank_account_number(bank_account_number).bank_code(bank_code).user_id(user_id).build();
    }

}
