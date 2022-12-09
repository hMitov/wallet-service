package com.paymentsystem.domain.value;

import com.paymentsystem.domain.entity.Currency;
import lombok.Data;

@Data
public class VBankAccountData {

    private String iban;

    private String cardNumber;

    private double availableAmount;

    private Currency currency;

    private Long customerId;

}
