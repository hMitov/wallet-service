package com.domain.value;

import com.domain.entity.Currency;
import lombok.Data;

@Data
public class VBankAccountData {

    private String iban;

    private String cardNumber;

    private double availableAmount;

    private Currency currency;

    private Long customerId;

}
