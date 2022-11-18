package com.springboot.webflux;

import com.springboot.webflux.config.TwilioConfig;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringBootWebfluxTwilioSmsNotificationApplication {

    @Autowired
    private TwilioConfig twilioConfig;

    @PostConstruct
    public void initTwilio() {
        Twilio.init(twilioConfig.getAccount_sid(),twilioConfig.getAuth_token());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebfluxTwilioSmsNotificationApplication.class, args);
    }

}
