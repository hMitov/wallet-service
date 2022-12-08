package com.paymentsystem.walletservice.domain.value;

import com.paymentsystem.walletservice.domain.entity.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VTransactionData {

    private String transactionId;

    private String recipientIban;

    private String senderIban;

    private LocalDateTime dateTimeOfTransaction;

    private double amount;

    private String recipientFirstName;

    private String recipientLastName;

    private Status status;

}
