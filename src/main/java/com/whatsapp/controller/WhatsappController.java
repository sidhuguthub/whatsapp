package com.whatsapp.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.whatsapp.payload.WhatsappRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WhatsappController {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.whatsapp.number}")
    private String twilioWhatsappNumber;



    @PostMapping("/send-whatsapp")
    public ResponseEntity<String> sendWhatsapp(@RequestBody WhatsappRequest whatsappRequest) {
        String body = whatsappRequest.getMessage();

        Twilio.init(accountSid, authToken);
        Message message = Message.creator(


                        new com.twilio.type.PhoneNumber("whatsapp:+919113935167"),
                        new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                        body)
                .create();

        System.out.println(message.getSid());

        return new ResponseEntity<>("Whatsapp message send successfully", HttpStatus.OK);
    }
}

