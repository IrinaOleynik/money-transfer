package ru.netology.moneytransfer.repository;

import org.springframework.stereotype.Repository;
import ru.netology.moneytransfer.dto.TransferRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OperationRepository {
    private Map<String, TransferRequest> successfulOperations = new ConcurrentHashMap<>();

    public void save(String operationId, TransferRequest request) {
        successfulOperations.put(operationId, request);
    }

    public boolean isSuccessful(String operationId) {
        if (successfulOperations.containsKey(operationId)) {
            return true;
        }
        return false;
    }
}
