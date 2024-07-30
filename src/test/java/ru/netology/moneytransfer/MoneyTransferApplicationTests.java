package ru.netology.moneytransfer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.testcontainers.containers.GenericContainer;
import ru.netology.moneytransfer.dto.TransferRequest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MoneyTransferApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;
    private static final GenericContainer<?> app = new GenericContainer<>("app:latest")
            .withExposedPorts(8080);

    @BeforeAll
    public static void setUp() {
        app.start();
    }

    @Test
    void testOkResponse() {
        HttpEntity<TransferRequest> request = new HttpEntity<>(new TransferRequest(
                "1111111111111111", "11/25", "111",
                "2222222222222222", new TransferRequest.Amount(10, "RUR")));
        String response = restTemplate.postForObject("http://localhost:" + app.getMappedPort(8080)
                + "/transfer", request, String.class);
        String expectedMessage = "operationId";
        assertNotNull(response);
        assertTrue(response.contains(expectedMessage));
    }

    @Test
    void testHandleInvalidCVVException() {
        HttpEntity<TransferRequest> request = new HttpEntity<>(new TransferRequest(
                "1111111111111111", "11/25", "333",
                "2222222222222222", new TransferRequest.Amount(10, "RUR")));
        String response = restTemplate.postForObject("http://localhost:" + app.getMappedPort(8080)
                + "/transfer", request, String.class);
        String expectedMessage = "Неверно указан CVV код";
        assertNotNull(response);
        assertTrue(response.contains(expectedMessage));
    }

    @Test
    void testHandleInvalidTillDateException() {
        HttpEntity<TransferRequest> request = new HttpEntity<>(new TransferRequest(
                "1111111111111111", "10/25", "111",
                "2222222222222222", new TransferRequest.Amount(10, "RUR")));
        String response = restTemplate.postForObject("http://localhost:" + app.getMappedPort(8080)
                + "/transfer", request, String.class);
        String expectedMessage = "Неверно указан срок действия карты";
        assertNotNull(response);
        assertTrue(response.contains(expectedMessage));
    }

    @Test
    void testHandleCardNumberNotFoundException() {
        TransferRequest transferRequest = (new TransferRequest(
                "1111111111111110", "11/25", "111",
                "2222222222222222", new TransferRequest.Amount(10, "RUR")));
        HttpEntity<TransferRequest> request = new HttpEntity<>(transferRequest);
        String response = restTemplate.postForObject("http://localhost:" + app.getMappedPort(8080)
                + "/transfer", request, String.class);
        String expectedMessage = "Карта отправителя " + transferRequest.getCardFromNumber() + " не найдена";
        assertNotNull(response);
        assertTrue(response.contains(expectedMessage));
    }

    @Test
    void testHandleLowBalanceException() {
        HttpEntity<TransferRequest> request = new HttpEntity<>(new TransferRequest(
                "1111111111111111", "11/25", "111",
                "2222222222222222", new TransferRequest.Amount(100000, "RUR")));
        String response = restTemplate.postForObject("http://localhost:" + app.getMappedPort(8080)
                + "/transfer", request, String.class);
        String expectedMessage = "На счёте недостаточно средств";
        assertNotNull(response);
        assertTrue(response.contains(expectedMessage));
    }

}
