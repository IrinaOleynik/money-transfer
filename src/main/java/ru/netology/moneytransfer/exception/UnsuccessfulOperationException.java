package ru.netology.moneytransfer.exception;

public class UnsuccessfulOperationException extends RuntimeException {
    public UnsuccessfulOperationException(String msg) {
        super(msg);
    }
}
