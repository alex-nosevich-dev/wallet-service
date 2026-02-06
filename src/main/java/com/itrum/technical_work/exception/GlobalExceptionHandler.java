package com.itrum.technical_work.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
//import com.itrum.technical_work.exception.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.itrum.technical_work.exception.WalletNotFoundException;
import com.itrum.technical_work.exception.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler (LowBalanceException.class)
    public ResponseEntity<ErrorResponse> handlerLowBalanceFunds (LowBalanceException e) {
        ErrorResponse body = new ErrorResponse(
                "LOW_BALANCE_FUNDS",
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerWalletNotFound(WalletNotFoundException e) {
        ErrorResponse body = new ErrorResponse(
                "WALLET_NOT_FOIND",
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
