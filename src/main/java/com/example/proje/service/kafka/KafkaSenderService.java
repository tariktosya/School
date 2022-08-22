package com.example.proje.service.kafka;

import com.example.proje.model.entity.City;
import com.example.proje.model.entity.User;
import com.example.proje.model.response.user.UserListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaSenderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSenderService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.name.producer}")
    private String topicName;

    public void send(List<User> user) {
        Map<String, Object> headers = new HashMap<>();
        headers.put(KafkaHeaders.TOPIC, topicName);
        kafkaTemplate.send(new GenericMessage<List<User>>(user, headers));
        // kafkaTemplate.send(topicName, "some string value")
        //LOGGER.info("Data - " + city.toString() + " sent to Kafka Topic - " + topicName);
    }

}
