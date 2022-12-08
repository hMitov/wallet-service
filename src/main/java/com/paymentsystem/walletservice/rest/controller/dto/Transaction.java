package com.paymentsystem.walletservice.rest.controller.dto;

import lombok.Data;

@Data
public class Transaction {

    private Long id;

    private TransactionData data;

}
