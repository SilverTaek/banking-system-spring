package com.bank.banking.toybank.api.bank.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("api/v1/bank-account")
public class BankAccountController {

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody final BankAccountRequest bankAccountRequest) {
         bankAccountService.create(bankAccountRequest);
    }
}
//
//    @PostMapping
//    @Login(admin = true)
//    public ResponseEntity<Void> create(@RequestBody final ProductCreateRequest productCreateRequest) {
//        final Long savedId = productService.save(productCreateRequest);
//        return ResponseEntity.created(URI.create("/api/v1/products/" + savedId))
//                .build();
//    }