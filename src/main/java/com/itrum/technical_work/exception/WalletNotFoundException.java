package com.itrum.technical_work.exception;
import java.util.UUID;
public class WalletNotFoundException extends RuntimeException {
    public WalletNotFoundException(UUID walletId) {
        super("Счет с ID " + walletId + " не найден");
    }
}
