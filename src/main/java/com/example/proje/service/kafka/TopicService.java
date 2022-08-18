package com.example.proje.service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicService {

    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(){
        String message = "Mesajimi almistir oooo Almasa da olur ooo";
        log.info("Payload enviado: {}",  message);
        kafkaTemplate.send(topicName, message);
    }

}
