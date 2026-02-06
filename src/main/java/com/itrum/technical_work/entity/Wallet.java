package com.itrum.technical_work.entity;

import jakarta.persistence.*;
import java.util.UUID;
import java.math.BigDecimal;

@Entity
@Table (name = "wallets")
public class Wallet {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    public Wallet() {

    }

    public Wallet(UUID id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
