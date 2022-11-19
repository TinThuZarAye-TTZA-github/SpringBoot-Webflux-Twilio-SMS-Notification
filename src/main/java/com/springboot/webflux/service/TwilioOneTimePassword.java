package com.springboot.webflux.service;

import com.springboot.webflux.config.TwilioConfig;
import com.springboot.webflux.dto.OtpResponse;
import com.springboot.webflux.dto.PasswordResetRequestDto;
import com.springboot.webflux.dto.PasswordResetResponseDto;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class TwilioOneTimePassword {

    @Autowired
    public TwilioConfig twilioConfig;

    Map<String,String> map = new HashMap<>();

    public Mono<PasswordResetResponseDto> sendOTPForPasswordReset(PasswordResetRequestDto passwordResetRequestDto) {
        PasswordResetResponseDto passwordRestResponseDto=null;
        try{
            PhoneNumber to = new PhoneNumber(passwordResetRequestDto.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrial_number());
            String oneTimePassword = generateOTP();
            String oneTimePasswordMessage = "Here is a new generated one time password :  " + oneTimePassword +".";
            Message message = Message.creator(to,from,oneTimePasswordMessage).create();

            map.put(passwordResetRequestDto.getUsername(),oneTimePassword);

            passwordRestResponseDto = new PasswordResetResponseDto(OtpResponse.DELIVERED,oneTimePasswordMessage);
        }catch (Exception ex) {
            passwordRestResponseDto = new PasswordResetResponseDto(OtpResponse.FAILED,"Error Message");
        }
        return Mono.just(passwordRestResponseDto);

    }

    //Created one time password generator
    private String generateOTP() {
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }

    public Mono<String> validateOTP(String username, String oneTimePassword) {
        if(oneTimePassword.equals(map.get(username))){
            return Mono.just("Validated");
        }else{
            return Mono.just("Not Validate");
        }
    }
}
