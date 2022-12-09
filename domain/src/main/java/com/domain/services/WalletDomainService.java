package com.domain.services;

import com.domain.WalletRepository;
import com.domain.adapter.DomainAdapter;
import com.domain.entity.Status;
import com.domain.value.VBankAccountData;
import com.domain.value.VCustomer;
import com.domain.value.VTransactionData;
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

    public VTransactionData createTransaction(List<VBankAccountData> vAccountData, List<VCustomer> vCustomers,
                                              VTransactionData vData) throws Exception {

        Optional<VBankAccountData> bankAccountData = vAccountData.stream()
                .filter(data -> data.getIban().equals(vData.getRecipientIban())).findFirst();

        Optional<VCustomer> recipient = vCustomers.stream()
                .filter(data -> data.getId().equals(bankAccountData.get().getCustomerId())).findFirst();

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
