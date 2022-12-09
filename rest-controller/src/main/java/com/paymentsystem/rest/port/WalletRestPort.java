package com.paymentsystem.rest.port;

import com.domain.services.WalletDomainService;
import com.domain.value.VBankAccount;
import com.domain.value.VBankAccountData;
import com.domain.value.VCustomer;
import com.domain.value.VTransactionData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentsystem.rest.adapter.RestAdapter;
import com.paymentsystem.rest.dto.Transaction;
import com.paymentsystem.rest.dto.TransactionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class WalletRestPort {

    @Autowired
    private WalletDomainService domainService;

    @Autowired
    private RestAdapter restAdapter;

    @Qualifier("billing")
    @Autowired
    private WebClient webClientBilling;

    @Qualifier("customers")
    @Autowired
    private WebClient webClientCustomers;

    public Transaction createTransaction() throws Exception {

        List<VBankAccountData> vBankAccounts =
                Arrays.stream(Objects.requireNonNull(webClientBilling.get().uri("/bank-accounts")
                                .retrieve().bodyToMono(Object[].class).block()))
                        .map(object -> new ObjectMapper()
                                .convertValue(object, VBankAccount.class)).map(VBankAccount::getData).collect(Collectors.toList());

        List<VCustomer> vCustomers =
                Arrays.stream(Objects.requireNonNull(webClientCustomers.get().uri("/customers")
                                .retrieve().bodyToMono(Object[].class).block()))
                        .map(object -> new ObjectMapper()
                                .convertValue(object, VCustomer.class)).collect(Collectors.toList());

        VTransactionData vData = new VTransactionData();
        vData.setRecipientIban("BG443322");
        vData.setSenderIban("BG178907");
        vData.setAmount(200.00);

        TransactionData createdTransaction = restAdapter
                .convertTransactionValueToDto(domainService.createTransaction(vBankAccounts, vCustomers, vData));

        return webClientBilling.post().uri("/transaction-create")
                .body(Mono.just(createdTransaction), TransactionData.class)
                .retrieve().bodyToMono(Transaction.class)
                .block();

    }

}
