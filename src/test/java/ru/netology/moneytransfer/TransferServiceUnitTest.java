package ru.netology.moneytransfer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.netology.moneytransfer.dto.TransferRequest;
import ru.netology.moneytransfer.dto.TransferResponse;
import ru.netology.moneytransfer.model.Card;
import ru.netology.moneytransfer.repository.CardRepository;
import ru.netology.moneytransfer.repository.OperationRepository;
import ru.netology.moneytransfer.service.TransferService;

import static org.mockito.Mockito.when;

class TransferServiceUnitTest {
    @Mock
    private CardRepository cardRepository;

    @Mock
    private OperationRepository operationRepository;

    @InjectMocks
    private TransferService transferService;
    private static TransferRequest request;

    @BeforeAll
    public static void formRequest() {
        request = new TransferRequest("1234567890123456",
                "12/24", "123", "9876543210987654",
                new TransferRequest.Amount(10, "RUR"));
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTransaction() {

        Card cardFrom = new Card("1234567890123456", "12/24", "123", "RUR", 100);
        Card cardTo = new Card("9876543210987654", "12/25", "222", "RUR", 100);

        when(cardRepository.findCardByNumber("1234567890123456")).thenReturn(cardFrom);
        when(cardRepository.findCardByNumber("9876543210987654")).thenReturn(cardTo);

        transferService.transaction(request);

        assertEquals(90, cardFrom.getAmount());
        assertEquals(110, cardTo.getAmount());

        verify(cardRepository, times(2)).findCardByNumber(anyString());
    }

    @Test
    void formOperationId() {
        TransferResponse response = transferService.formOperationId(request);

        assertNotNull(response.getOperationId());
        verify(operationRepository, times(1)).save(anyString(), eq(request));
    }

    @Test
    void confirmSuccessfulOperation() {
        when(operationRepository.isSuccessful("12345")).thenReturn(true);

        transferService.confirmSuccessfulOperation("12345");

        verify(operationRepository, times(1)).isSuccessful("12345");
    }
}

