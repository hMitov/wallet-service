package com.paymentsystem.rest.adapter;

import com.domain.value.VBankAccountData;
import com.domain.value.VCustomer;
import com.domain.value.VCustomerData;
import com.domain.value.VTransactionData;
import com.paymentsystem.rest.dto.BankAccountData;
import com.paymentsystem.rest.dto.Customer;
import com.paymentsystem.rest.dto.TransactionData;
import org.springframework.stereotype.Component;

@Component
public class RestAdapter {

    public TransactionData convertTransactionValueToDto(VTransactionData vData) {

        TransactionData data = new TransactionData();

        data.setRecipientIban(vData.getRecipientIban());
        data.setSenderIban(vData.getSenderIban());
        data.setDateTimeOfTransaction(vData.getDateTimeOfTransaction());
        data.setAmount(vData.getAmount());
        data.setRecipientFirstName(vData.getRecipientFirstName());
        data.setRecipientLastName(vData.getRecipientLastName());
        data.setStatus(vData.getStatus());

        return data;

    }
    public VTransactionData convertTransactionDtoToValue(TransactionData data) {

        VTransactionData vData = new VTransactionData();

        vData.setTransactionId(data.getTransactionId());
        vData.setRecipientIban(data.getRecipientIban());
        vData.setSenderIban(data.getSenderIban());
        vData.setDateTimeOfTransaction(data.getDateTimeOfTransaction());
        vData.setAmount(data.getAmount());
        vData.setRecipientFirstName(data.getRecipientFirstName());
        vData.setRecipientLastName(data.getRecipientLastName());
        vData.setStatus(data.getStatus());

        return vData;

    }



    public VBankAccountData convertBankAccountDataDtoToValue(BankAccountData data) {

        VBankAccountData vData = new VBankAccountData();

        vData.setIban(data.getIban());
        vData.setCardNumber(data.getCardNumber());
        vData.setAvailableAmount(vData.getAvailableAmount());
        vData.setCurrency(data.getCurrency());
        vData.setCustomerId(data.getCustomerId());

        return vData;

    }
    public VCustomer convertCustomerDtoToValue(Customer customer) {

        VCustomer vCustomer = new VCustomer();
        VCustomerData vData = new VCustomerData();

        vData.setFirstName(customer.getData().getFirstName());
        vData.setLastName(customer.getData().getLastName());
        vData.setAge(customer.getData().getAge());
        vData.setGender(customer.getData().getGender());
        vData.setCountry(customer.getData().getCountry());
        vData.setCity(customer.getData().getCity());
        vData.setNameOfStreet(customer.getData().getNameOfStreet());
        vData.setNumberOfBuilding(customer.getData().getNumberOfBuilding());
        vData.setPostCode(customer.getData().getPostCode());
        vData.setTelephone(customer.getData().getTelephone());
        vData.setEmail(customer.getData().getEmail());

        vCustomer.setId(customer.getId());
        vCustomer.setData(vData);

        return vCustomer;

    }


}
