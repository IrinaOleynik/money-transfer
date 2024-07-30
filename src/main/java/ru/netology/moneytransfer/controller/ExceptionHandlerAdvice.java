package ru.netology.moneytransfer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.moneytransfer.dto.ErrorResponse;
import ru.netology.moneytransfer.exception.*;

import java.util.concurrent.atomic.AtomicInteger;


@RestControllerAdvice
public class ExceptionHandlerAdvice {
    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
    private final AtomicInteger errorIdCounter = new AtomicInteger(0);

    @ExceptionHandler(CardNumberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCardNumberNotFoundException(CardNumberNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), errorIdCounter.incrementAndGet());
        logger.error("Error ID: {}, \n CardNumberNotFoundException: {}", errorResponse.getId(), errorResponse.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(InvalidCVVException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCVVException(InvalidCVVException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), errorIdCounter.incrementAndGet());
        logger.error("Error ID: {}, \n InvalidCVVException : {}", errorResponse.getId(), errorResponse.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(InvalidTillDateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTillDateException(InvalidTillDateException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), errorIdCounter.incrementAndGet());
        logger.error("Error ID: {}, \n InvalidTillDateException: {}", errorResponse.getId(), errorResponse.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(LowBalanceException.class)
    public ResponseEntity<ErrorResponse> handleLowBalanceException(LowBalanceException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), errorIdCounter.incrementAndGet());
        logger.error("Error ID: {}, \n LowBalanceException: {}", errorResponse.getId(), errorResponse.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(UnsuccessfulOperationException.class)
    public ResponseEntity<ErrorResponse> handleUnsuccessfulOperationException(UnsuccessfulOperationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), errorIdCounter.incrementAndGet());
        logger.error("Error ID: {}, \n UnsuccessfulOperationException: {}", errorResponse.getId(), errorResponse.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), errorIdCounter.incrementAndGet());
        logger.error("Error ID: {}, \n MethodArgumentNotValidException: {}", errorResponse.getId(), errorResponse.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleServerException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), errorIdCounter.incrementAndGet());
        logger.error("Error ID: {}, \n ServerException: {}", errorResponse.getId(), errorResponse.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
