package com.paymentsystem.domain.value;

import lombok.Data;

import java.util.Date;

@Data
public class VWalletData {

    private Date dateOfCreation;

    private Long customerId;

    private Long bankAccount;
}
