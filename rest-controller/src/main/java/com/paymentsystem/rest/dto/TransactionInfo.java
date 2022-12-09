package com.paymentsystem.rest.dto;

import com.domain.entity.Status;
import lombok.Data;

@Data
public class TransactionInfo {

    private String recipientIban;

    private String senderIban;

    private double amount;

    private Status status;

}
