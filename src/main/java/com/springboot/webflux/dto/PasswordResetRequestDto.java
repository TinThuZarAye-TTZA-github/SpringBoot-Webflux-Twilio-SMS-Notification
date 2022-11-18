package com.springboot.webflux.dto;

import lombok.Data;

@Data
public class PasswordResetRequestDto {

    private String phoneNumber; // registered phone number to Twilio
    private String username;
    private String oneTimePassword;
}
