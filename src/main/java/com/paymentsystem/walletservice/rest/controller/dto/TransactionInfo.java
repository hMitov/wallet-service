package com.paymentsystem.walletservice.rest.controller.dto;

import com.paymentsystem.walletservice.domain.entity.Status;
import lombok.Data;

@Data
public class TransactionInfo {

    private String recipientIban;

    private String senderIban;

    private double amount;

    private Status status;

}
