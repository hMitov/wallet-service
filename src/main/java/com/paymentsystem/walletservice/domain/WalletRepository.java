package com.paymentsystem.walletservice.domain;

import com.paymentsystem.walletservice.domain.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByCustomerId(Long id);

}
