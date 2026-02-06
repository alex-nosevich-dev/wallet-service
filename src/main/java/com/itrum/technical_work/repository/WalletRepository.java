package com.itrum.technical_work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.itrum.technical_work.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {

}
