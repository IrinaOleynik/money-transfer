package ru.netology.moneytransfer.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TransferRequest {
    @NotNull
    @Size(min = 16, max = 16)
    private String cardFromNumber;
    @NotNull
    private String cardFromValidTill;
    @NotNull
    @Size(min = 3, max = 3)
    private String cardFromCVV;
    @NotNull
    @Size(min = 16, max = 16)
    private String cardToNumber;
    @NotNull
    private Amount amount;

    public TransferRequest(String cardFromNumber, String cardFromValidTill, String cardFromCVV, String cardToNumber, Amount amount) {
        this.cardFromNumber = cardFromNumber;
        this.cardFromValidTill = cardFromValidTill;
        this.cardFromCVV = cardFromCVV;
        this.cardToNumber = cardToNumber;
        this.amount = amount;
    }

    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public String getCardFromCVV() {
        return cardFromCVV;
    }

    public String getCardFromValidTill() {
        return cardFromValidTill;
    }

    public String getCardToNumber() {
        return cardToNumber;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setCardFromNumber(@NotNull @Size(min = 16, max = 16) String cardFromNumber) {
        this.cardFromNumber = cardFromNumber;
    }

    public void setCardFromCVV(@NotNull @Size(min = 3, max = 3) String cardFromCVV) {
        this.cardFromCVV = cardFromCVV;
    }

    public void setCardFromValidTill(@NotNull String cardFromValidTill) {
        this.cardFromValidTill = cardFromValidTill;
    }

    public void setCardToNumber(@NotNull @Size(min = 16, max = 16) String cardToNumber) {
        this.cardToNumber = cardToNumber;
    }

    public void setAmount(@NotNull Amount amount) {
        this.amount = amount;
    }

    public static class Amount {
        private Integer value;
        private String currency;

        public Amount(Integer value, String currency) {
            this.value = value;
            this.currency = currency;
        }

        public Integer getValue() {
            return value;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }
}
