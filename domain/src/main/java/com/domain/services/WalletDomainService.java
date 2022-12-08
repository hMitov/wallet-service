package com.domain.services;

import com.domain.WalletRepository;
import com.domain.adapter.DomainAdapter;
import com.domain.entity.Status;
import com.domain.value.VTransactionData;
import com.restcontroller.dto.BankAccountData;
import com.restcontroller.dto.Customer;
import com.restcontroller.dto.TransactionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class WalletDomainService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private DomainAdapter domainAdapter;

    public VTransactionData createTransaction(List<BankAccountData> accountData, List<Customer> customers,
                                              TransactionInfo transactionInfo) throws Exception {

        Optional<BankAccountData> bankAccountData = accountData.stream()
                .filter(data -> data.getIban().equals(transactionInfo.getRecipientIban())).findFirst();

        Optional<Customer> recipient = customers.stream()
                .filter(data -> data.getId().equals(bankAccountData.get().getCustomerId())).findFirst();

        VTransactionData data = new VTransactionData();
        data.setRecipientIban(transactionInfo.getRecipientIban());
        data.setSenderIban(transactionInfo.getSenderIban());
        data.setDateTimeOfTransaction(LocalDateTime.now());
        data.setAmount(transactionInfo.getAmount());
        data.setRecipientFirstName(recipient.get().getData().getFirstName());
        data.setRecipientLastName(recipient.get().getData().getLastName());
        data.setStatus(Status.PENDING);

        return data;
    }

}
