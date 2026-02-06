package com.itrum.technical_work.controller;

import com.itrum.technical_work.dto.WalletOperationRequest;
import com.itrum.technical_work.service.WalletService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.itrum.technical_work.dto.WalletBalanceResponse;
import com.itrum.technical_work.dto.WalletOperationRequest;
import com.itrum.technical_work.service.WalletService;
import java.util.UUID;
import java.math.BigDecimal;


@RestController
@RequestMapping("/api/v1")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallet")
    public ResponseEntity<Void> operation(@RequestBody WalletOperationRequest request) {
        walletService.operationProcess(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/wallets/{walletId}")
    public ResponseEntity<WalletBalanceResponse> getBalance(@PathVariable
                                                            UUID walletId) {
        WalletBalanceResponse response = walletService.getBalance(walletId);
        return ResponseEntity.ok(response);

    }

}
