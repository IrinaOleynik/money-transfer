package ru.netology.moneytransfer.model;

public class Card {
    private String number;
    private String validTill;
    private String cvv;
    private String currency;
    private int amount;

    public Card(String number, String validTill, String cvv, String currency, int amount) {
        this.number = number;
        this.validTill = validTill;
        this.cvv = cvv;
        this.currency = currency;
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public String getValidTill() {
        return validTill;
    }

    public String getCVV() {
        return cvv;
    }

    public String getCurrency() {
        return currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
