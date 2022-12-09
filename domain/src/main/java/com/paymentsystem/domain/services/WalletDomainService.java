package com.paymentsystem.domain.services;

import com.paymentsystem.domain.adapter.DomainAdapter;
import com.paymentsystem.domain.entity.Status;
import com.paymentsystem.domain.repository.WalletRepository;
import com.paymentsystem.domain.value.VBankAccountData;
import com.paymentsystem.domain.value.VCustomer;
import com.paymentsystem.domain.value.VTransactionData;
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

    public VTransactionData createTransaction(List<VBankAccountData> vAccountsData, List<VCustomer> vCustomers,
                                              VTransactionData vData) throws Exception {

        Optional<VBankAccountData> bankAccount = vAccountsData.stream()
                .filter(data -> data.getIban().equals(vData.getRecipientIban())).findFirst();

        Optional<VCustomer> recipient = vCustomers.stream()
                .filter(data -> data.getId().equals(bankAccount.get().getCustomerId())).findFirst();

        VTransactionData data = new VTransactionData();
        data.setRecipientIban(vData.getRecipientIban());
        data.setSenderIban(vData.getSenderIban());
        data.setDateTimeOfTransaction(LocalDateTime.now());
        data.setAmount(vData.getAmount());
        data.setRecipientFirstName(recipient.get().getData().getFirstName());
        data.setRecipientLastName(recipient.get().getData().getLastName());
        data.setStatus(Status.PENDING);

        return data;
    }

}
