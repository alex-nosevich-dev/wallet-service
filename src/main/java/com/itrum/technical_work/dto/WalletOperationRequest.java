package com.itrum.technical_work.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class WalletOperationRequest {
    private UUID walletId;
    private OperationType operationType;
    private BigDecimal amount;

    public enum OperationType {
        DEPOSIT,
        WITHDRAW
    }

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}
