package com.itrum.technical_work;


import com.itrum.technical_work.dto.WalletBalanceResponse;
import com.itrum.technical_work.dto.WalletOperationRequest;
import com.itrum.technical_work.entity.Wallet;
import com.itrum.technical_work.exception.LowBalanceException;
import com.itrum.technical_work.exception.WalletNotFoundException;
import com.itrum.technical_work.repository.WalletRepository;
import com.itrum.technical_work.service.WallerServiceImplement;
import com.itrum.technical_work.service.WalletService;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class WallerServiceImplementTest {
    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private WallerServiceImplement walletService;

    @Test
    void whenWithdrawMoreThanBalanceTest() {
        UUID walletId = UUID.randomUUID();
        BigDecimal balance = new BigDecimal("50.00");
        Wallet wallet = new Wallet(walletId, balance);
        when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));
        BigDecimal amount = new BigDecimal("100.00");
        WalletOperationRequest operationRequest = new WalletOperationRequest();
        operationRequest.setOperationType(WalletOperationRequest.OperationType.WITHDRAW);
        operationRequest.setWalletId(walletId);
        operationRequest.setAmount(amount);
        //walletService.operationProcess(operationRequest);
        //assertThrows(LowBalanceException.class, () -> balance.compareTo(amount));
        assertThrows(LowBalanceException.class, () -> walletService.operationProcess(operationRequest));

    }

    @Test
    void depositTest() {

        UUID walletId = UUID.randomUUID();
        Wallet wallet = new Wallet(walletId, BigDecimal.ZERO);
        when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));
       // WalletOperationRequest.OperationType.DEPOSIT;
        BigDecimal amount = new BigDecimal("100.00");
        WalletOperationRequest operationRequest = new WalletOperationRequest();
        operationRequest.setOperationType(WalletOperationRequest.OperationType.DEPOSIT);
        operationRequest.setAmount(amount);
        operationRequest.setWalletId(walletId);
        walletService.operationProcess(operationRequest);
        assertEquals(new BigDecimal("100.00"), wallet.getBalance());

    }

    @Test
    void sucsessWithdraw() {
        BigDecimal balance = new BigDecimal("100.00");
        UUID walletId = UUID.randomUUID();
        Wallet wallet = new Wallet(walletId, balance);
        when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));
        BigDecimal amount = new BigDecimal("50.00");
        WalletOperationRequest operationRequest = new WalletOperationRequest();
        operationRequest.setAmount(amount);
        operationRequest.setOperationType(WalletOperationRequest.OperationType.WITHDRAW);
        operationRequest.setWalletId(walletId);
        walletService.operationProcess(operationRequest);
        assertEquals(new BigDecimal("50.00"), wallet.getBalance());
    }

    @Test
    void sucsessGetBalance() {
        BigDecimal balance = new BigDecimal("100.00");
        UUID walletId = UUID.randomUUID();
        Wallet wallet = new Wallet(walletId, balance);
        when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));

        WalletBalanceResponse balanceResponse = walletService.getBalance(walletId);

        assertEquals(walletId, balanceResponse.getWalletId());
    }

    @Test
    void errorGetBalance() {
        BigDecimal balance = new BigDecimal("100.00");
        UUID walletId = UUID.randomUUID();
        Wallet wallet = new Wallet(walletId, balance);
        when(walletRepository.findById(walletId)).thenReturn(Optional.empty());
       // WalletBalanceResponse balanceResponse = walletService.getBalance(walletId);

        assertThrows(WalletNotFoundException.class, () -> walletService.getBalance(walletId));
    }
}
