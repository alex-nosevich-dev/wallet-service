package com.itrum.technical_work.exception;

public class LowBalanceException extends  RuntimeException{
    public LowBalanceException(String message) {
        super(message);
    }
}

