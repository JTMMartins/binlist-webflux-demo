package com.noshio.webfluxdemo.controller;

import com.noshio.webfluxdemo.converters.CardDetailsConverter;
import com.noshio.webfluxdemo.dto.CardApiResponse;
import com.noshio.webfluxdemo.dto.ValidateCardResponse;
import com.noshio.webfluxdemo.error.BinListCommunicationException;
import com.noshio.webfluxdemo.error.ErrorResponse;
import com.noshio.webfluxdemo.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CardVerifyController {

    private final CardService cardService;
    private final CardDetailsConverter cardDetailsConverter;

    @GetMapping("card-scheme/verify/{cardnumber}")
    public Mono<ValidateCardResponse> validate(@PathVariable("cardnumber") String cardNumber) {
        final Mono<CardApiResponse> cardApiResponse = cardService.validateCard(cardNumber);
        return cardDetailsConverter.convert(cardApiResponse);
    }

    @ExceptionHandler(BinListCommunicationException.class)
    public ResponseEntity<ErrorResponse> handleException(final BinListCommunicationException e) {
        log.error("Card validation error {}", e);
        final ErrorResponse errorResponse = new ErrorResponse(false, e.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
