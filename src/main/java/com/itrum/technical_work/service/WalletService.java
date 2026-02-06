package com.itrum.technical_work.service;

import java.math.BigDecimal;
import java.util.UUID;
import com.itrum.technical_work.dto.WalletBalanceResponse;
import com.itrum.technical_work.dto.WalletOperationRequest;

public interface WalletService {
    void operationProcess(WalletOperationRequest request);
    WalletBalanceResponse getBalance(UUID walletId);
}
