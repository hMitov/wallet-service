package com.paymentsystem.walletservice.rest.controller.dto;

import com.paymentsystem.walletservice.domain.entity.Currency;
import lombok.Data;

@Data
public class BankAccountData {

    private String iban;

    private String cardNumber;

    private double availableAmount;

    private Currency currency;

    private Long customerId;

}
