package com.noshio.webfluxdemo.service;

import com.noshio.webfluxdemo.dto.CardApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service

public class CardService {

    private static final String BINLIST_BASE_ENDPOINT = "https://lookup.binlist.net/";

    public Mono<CardApiResponse> validateCard(final String cardNumber) {

        WebClient webClient = WebClient.create(BINLIST_BASE_ENDPOINT);

        return webClient.get()
                .uri(cardNumber)
                .retrieve()
                .bodyToMono(CardApiResponse.class);

    }

}
