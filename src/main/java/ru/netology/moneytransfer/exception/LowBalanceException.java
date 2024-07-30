package ru.netology.moneytransfer.exception;

public class LowBalanceException extends RuntimeException {
    public LowBalanceException(String msg) {
        super(msg);
    }
}
