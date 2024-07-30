package ru.netology.moneytransfer.exception;

public class InvalidCVVException extends RuntimeException {
    public InvalidCVVException(String msg) {
        super(msg);
    }
}
