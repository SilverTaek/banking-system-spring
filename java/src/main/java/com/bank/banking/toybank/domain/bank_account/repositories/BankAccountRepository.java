package com.bank.banking.toybank.domain.bank_account.repositories;

import com.bank.banking.toybank.domain.bank_account.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

//    public String create() {
//        return "정상적으로 생성되었습니다.";
//    }
}
