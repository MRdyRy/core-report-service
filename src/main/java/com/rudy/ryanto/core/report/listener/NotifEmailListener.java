package com.rudy.ryanto.core.report.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudy.ryanto.core.report.domain.Email;
import com.rudy.ryanto.core.report.services.NotifService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotifEmailListener {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NotifService notifService;

    @KafkaListener(topics = "email-topic")
    public <T> void sendBasicEmail(T data) throws JsonProcessingException {
        log.info("message received, process to send basic email with data : {}", data);
        Email email = getEmailObject(String.valueOf(data));
        if (null != email.getAttactment() && !"".equalsIgnoreCase(email.getAttactment()))
            notifService.sendPlainMessage(email);
        else
            notifService.sendAttachmentMessage(email);
    }

    private Email getEmailObject(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, Email.class);
    }
}
