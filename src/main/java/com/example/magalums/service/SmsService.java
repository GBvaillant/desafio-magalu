package com.example.magalums.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.account.sid}")
    private String accountId;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String phoneNumber;

    @PostConstruct
    public void init () {
        Twilio.init(accountId, authToken);
    }

    public void sendSms(String to, String message) {
        Message.creator(
                new PhoneNumber("+55" + to),
                new PhoneNumber(phoneNumber),
                message
        ).create();
    }
}
