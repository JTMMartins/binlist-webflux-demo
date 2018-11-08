package com.noshio.webfluxdemo.converters;

import com.noshio.webfluxdemo.dto.CardApiResponse;
import com.noshio.webfluxdemo.dto.ValidateCardResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class CardDetailsConverter implements Converter<Mono<CardApiResponse>, Mono<ValidateCardResponse>> {

    @Override
    public Mono<ValidateCardResponse> convert(final Mono<CardApiResponse> apiResponse) {
        return apiResponse.map(
                response ->
                        ValidateCardResponse.builder()
                                .success(true)
                                .payload(ValidateCardResponse.Payload.builder()
                                        .scheme(response.getScheme())
                                        .type(response.getType())
                                        .bank(Optional.ofNullable(response.getBank())
                                                .orElse(null).getName()).build()).build());
    }
}
