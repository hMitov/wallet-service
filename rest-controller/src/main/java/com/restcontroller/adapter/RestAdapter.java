package com.restcontroller.adapter;

import com.domain.value.VTransactionData;
import com.restcontroller.dto.TransactionData;
import org.springframework.stereotype.Component;

@Component
public class RestAdapter {

    public TransactionData convertTransactionValueToDto(VTransactionData vData) {

        TransactionData data = new TransactionData();

        data.setRecipientIban(vData.getRecipientIban());
        data.setSenderIban(vData.getSenderIban());
        data.setDateOfTransaction(vData.getDateTimeOfTransaction());
        data.setAmount(vData.getAmount());
        data.setRecipientFirstName(vData.getRecipientFirstName());
        data.setRecipientLastName(vData.getRecipientLastName());
        data.setStatus(vData.getStatus());

        return data;

    }

}
