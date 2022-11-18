package com.springboot.webflux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRestResponseDto {

    private OtpResponse otpResponse;
    private String message;
}
