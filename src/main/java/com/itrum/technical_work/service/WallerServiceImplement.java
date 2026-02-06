package com.itrum.technical_work.service;

import com.itrum.technical_work.dto.WalletBalanceResponse;
import com.itrum.technical_work.dto.WalletOperationRequest;
import com.itrum.technical_work.entity.Wallet;
import com.itrum.technical_work.exception.WalletNotFoundException;
import com.itrum.technical_work.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.itrum.technical_work.exception.LowBalanceException;

import  java.math.BigDecimal;
import java.util.UUID;


@Service
public class WallerServiceImplement implements WalletService{
    private final WalletRepository walletRepository;

    public WallerServiceImplement(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    @Transactional
    public void operationProcess(WalletOperationRequest request) {
        UUID id = request.getWalletId();
        BigDecimal amount = request.getAmount();
        WalletOperationRequest.OperationType type = request.getOperationType();



        if(type == WalletOperationRequest.OperationType.DEPOSIT) {
            Wallet wallet = walletRepository.findById(id)
                    .orElseGet(() -> new Wallet(id, BigDecimal.ZERO));

            BigDecimal currentBalance = wallet.getBalance();
            wallet.setBalance(wallet.getBalance().add(amount));
        }

        else if (type == WalletOperationRequest.OperationType.WITHDRAW) {
            Wallet wallet = walletRepository.findById(id)
                    .orElseThrow(() -> new WalletNotFoundException(id));
            BigDecimal currentBalance = wallet.getBalance();
            if (currentBalance.compareTo(amount) < 0) {
                throw new LowBalanceException("Недостаточно средств для снятия");
            }
            wallet.setBalance(wallet.getBalance().subtract(amount));
            walletRepository.save(wallet);
        }


    }

    @Override
    @Transactional
    public WalletBalanceResponse getBalance(UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(()-> new WalletNotFoundException(walletId));
        return new WalletBalanceResponse(wallet.getId(), wallet.getBalance());

    }
}


