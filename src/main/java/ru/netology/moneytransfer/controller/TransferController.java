package ru.netology.moneytransfer.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.moneytransfer.dto.ConfirmOperationRequest;
import ru.netology.moneytransfer.dto.TransferRequest;
import ru.netology.moneytransfer.dto.TransferResponse;
import ru.netology.moneytransfer.service.TransferService;

@RestController
@CrossOrigin(origins = {"https://serp-ya.github.io", "http://localhost:3000"})
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public TransferResponse transfer(@Valid @RequestBody TransferRequest request) {
        transferService.transaction(request);
        TransferResponse response = transferService.formOperationId(request);
        return response;
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<?> confirmOperation(@Valid @RequestBody ConfirmOperationRequest request) {
        String operationId = request.getOperationId();
        transferService.confirmSuccessfulOperation(operationId);
        TransferResponse response = new TransferResponse(operationId);
        return ResponseEntity.ok(response);
    }
}
