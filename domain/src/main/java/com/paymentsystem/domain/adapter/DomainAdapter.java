package com.paymentsystem.domain.adapter;

import com.paymentsystem.domain.entity.Wallet;
import com.paymentsystem.domain.value.VWallet;
import com.paymentsystem.domain.value.VWalletData;
import org.springframework.stereotype.Component;

@Component
public class DomainAdapter {

    public VWallet convertWalletEntityToValue(Wallet wallet) {

        VWallet vWallet = new VWallet();
        VWalletData vData = new VWalletData();

        vData.setDateOfCreation(wallet.getDateOfCreation());
        vData.setCustomerId(wallet.getCustomerId());
        vData.setBankAccount(wallet.getBankAccount());

        vWallet.setId(wallet.getId());
        vWallet.setData(vData);

        return vWallet;
    }

    public Wallet convertWalletValueToEntity(VWallet vWallet) {

        Wallet wallet = new Wallet();

        wallet.setId(vWallet.getId());
        wallet.setDateOfCreation(vWallet.getData().getDateOfCreation());
        wallet.setCustomerId(vWallet.getData().getCustomerId());
        wallet.setBankAccount(vWallet.getData().getBankAccount());

        return wallet;

    }

}
