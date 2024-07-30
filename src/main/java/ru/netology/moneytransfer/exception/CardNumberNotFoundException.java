package ru.netology.moneytransfer.exception;

public class CardNumberNotFoundException extends RuntimeException {
    public CardNumberNotFoundException(String msg) {
        super(msg);
    }
}
