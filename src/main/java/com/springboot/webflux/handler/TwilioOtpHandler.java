package com.springboot.webflux.handler;

import com.springboot.webflux.dto.PasswordResetRequestDto;
import com.springboot.webflux.service.TwilioOneTimePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component

public class TwilioOtpHandler {

    @Autowired
    private TwilioOneTimePassword twilioOneTimePassword;

    public Mono<ServerResponse> sendOTP(ServerRequest request) {
        return request.bodyToMono(PasswordResetRequestDto.class)
                .flatMap(dto -> twilioOneTimePassword.sendOTPForPaswwordReset(dto))

                .flatMap(dto -> ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(dto)));
    }

    public Mono<ServerResponse> validateOTP(ServerRequest request) {
        return request.bodyToMono(PasswordResetRequestDto.class)
                .flatMap(dto -> twilioOneTimePassword.validateOTP(dto.getUsername(),dto.getOneTimePassword()))
                .flatMap(dto -> ServerResponse.status(HttpStatus.OK).bodyValue(dto));
    }

}
