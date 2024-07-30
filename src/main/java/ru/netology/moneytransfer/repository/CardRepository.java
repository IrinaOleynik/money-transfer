package ru.netology.moneytransfer.repository;

import org.springframework.stereotype.Repository;
import ru.netology.moneytransfer.model.Card;

import java.util.Map;

@Repository
public class CardRepository {

    private final Map<String, Card> cards = Map.of("1111111111111111",
            new Card("1111111111111111", "11/25", "111", "RUR", 100),
            "2222222222222222", new Card("2222222222222222", "12/25", "222", "RUR", 100));


    public Card findCardByNumber(String cardNumber) {
        return cards.get(cardNumber);
    }
}
