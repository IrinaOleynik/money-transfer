package ru.netology.moneytransfer.exception;

public class InvalidTillDateException extends RuntimeException {
    public InvalidTillDateException(String msg) {
        super(msg);
    }
}
