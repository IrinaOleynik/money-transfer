package ru.netology.moneytransfer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.netology.moneytransfer.dto.TransferRequest;
import ru.netology.moneytransfer.dto.TransferResponse;
import ru.netology.moneytransfer.exception.CardNumberNotFoundException;
import ru.netology.moneytransfer.exception.InvalidCVVException;
import ru.netology.moneytransfer.exception.UnsuccessfulOperationException;
import ru.netology.moneytransfer.model.Card;
import ru.netology.moneytransfer.repository.CardRepository;
import ru.netology.moneytransfer.repository.OperationRepository;

import java.util.UUID;

@Service
public class TransferService {
    private final CardRepository cardRepository;
    private final OperationRepository operationRepository;
    private final Logger logger = LoggerFactory.getLogger(TransferService.class);

    public TransferService(CardRepository cardRepository, OperationRepository operationRepository) {
        this.cardRepository = cardRepository;
        this.operationRepository = operationRepository;
    }

    public void transaction(TransferRequest request) {

        logger.info("Начало операции с карты отправителя: {}, \n на карту получателя: {}",
                request.getCardFromNumber(), request.getCardToNumber());
        Card cardFrom = cardRepository.findCardByNumber(request.getCardFromNumber());
        if (cardFrom == null) {
            throw new CardNumberNotFoundException("Карта отправителя " + request.getCardFromNumber() + " не найдена");
        }
        Card cardTo = cardRepository.findCardByNumber(request.getCardToNumber());
        if (cardTo == null) {
            throw new CardNumberNotFoundException("Карта получателя " + request.getCardToNumber() + " не найдена");
        }
        if (!cardFrom.getCVV().equals(request.getCardFromCVV())) {
            throw new InvalidCVVException("Неверно указан CVV код");
        }
        if (!cardFrom.getValidTill().equals(request.getCardFromValidTill())) {
            throw new InvalidCVVException("Неверно указан срок действия карты");
        }
        if (cardFrom.getAmount() < request.getAmount().getValue()) {
            throw new InvalidCVVException("На счёте недостаточно средств");
        }

        cardFrom.setAmount(cardFrom.getAmount() - request.getAmount().getValue());
        cardTo.setAmount(cardTo.getAmount() + request.getAmount().getValue());
        logger.info("Успешная операция c карты отправителя: {},\n на карту получателя: {}",
                request.getCardFromNumber(), request.getCardToNumber());
    }

    public TransferResponse formOperationId(TransferRequest request) {
        String operationId = UUID.randomUUID().toString();
        operationRepository.save(operationId, request);
        logger.info("ID операции: {}", operationId);
        return new TransferResponse(operationId);
    }

    public void confirmSuccessfulOperation(String operationId) {
        if (!operationRepository.isSuccessful(operationId)) {
            throw new UnsuccessfulOperationException("Произошла ошибка при выполнении операции");
        }
    }
}

