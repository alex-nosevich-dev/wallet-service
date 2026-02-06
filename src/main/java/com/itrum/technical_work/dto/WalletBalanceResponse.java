package com.itrum.technical_work.dto;

import java.util.UUID;
import java.math.BigDecimal;
public class WalletBalanceResponse {
    private UUID walletId;
    private BigDecimal balance;


    public WalletBalanceResponse(UUID walletId, BigDecimal balance) {
        this.balance = balance;
        this.walletId = walletId;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
