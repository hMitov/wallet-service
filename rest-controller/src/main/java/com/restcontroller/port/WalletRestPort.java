package com.restcontroller.port;

import com.domain.services.WalletDomainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restcontroller.adapter.RestAdapter;
import com.restcontroller.dto.*;
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

        List<BankAccountData> bankAccounts =
                Arrays.stream(Objects.requireNonNull(webClientBilling.get().uri("/bank-accounts")
                                .retrieve().bodyToMono(Object[].class).block()))
                        .map(object -> new ObjectMapper()
                .convertValue(object, BankAccount.class)).map(BankAccount::getData).collect(Collectors.toList());

        List<Customer> customers =
                Arrays.stream(Objects.requireNonNull(webClientCustomers.get().uri("/customers")
                                .retrieve().bodyToMono(Object[].class).block()))
                        .map(object -> new ObjectMapper()
                .convertValue(object, Customer.class)).collect(Collectors.toList());

        TransactionInfo transactionInfo = new TransactionInfo();
        transactionInfo.setRecipientIban("BG443322");
        transactionInfo.setSenderIban("BG178907");
        transactionInfo.setAmount(200.00);

        TransactionData createdTransaction = restAdapter
                .convertTransactionValueToDto(domainService.createTransaction(bankAccounts, customers, transactionInfo));

        return webClientBilling.post().uri("/transaction-create")
                .body(Mono.just(createdTransaction), TransactionData.class)
                .retrieve().bodyToMono(Transaction.class)
                .block();

    }

}
