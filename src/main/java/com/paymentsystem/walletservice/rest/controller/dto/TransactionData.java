package com.paymentsystem.walletservice.rest.controller.dto;

import com.paymentsystem.walletservice.domain.entity.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionData {

    private String transactionId;

    private String recipientIban;

    private String senderIban;

    private LocalDateTime dateOfTransaction;

    private double amount;

    private String recipientFirstName;

    private String recipientLastName;

    private Status status;

}
