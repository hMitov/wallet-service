package com.paymentsystem.domain.repository;

import com.paymentsystem.domain.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByCustomerId(Long id);

}
