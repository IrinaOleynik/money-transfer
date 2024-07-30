package ru.netology.moneytransfer.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ConfirmOperationRequest {
    @NotNull
    @Size(min = 36, max = 36)
    private String operationId;
    @NotNull
    private String code;

    public ConfirmOperationRequest(String operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }

    public String getOperationId() {
        return operationId;
    }

    public String getCode() {
        return code;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public void setCode(String code) {
        this.code = code;
    }
}