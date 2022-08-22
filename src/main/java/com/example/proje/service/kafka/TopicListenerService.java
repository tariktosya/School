package com.example.proje.service.kafka;

import com.example.proje.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TopicListenerService {

    @Value("${topic.name.consumer}")
    private String topicName;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "userListener")
    void listener(List<User> data, ConsumerRecord<String, List<User>> payload) {
        payload.value().forEach(o -> System.out.println("UserId : " + o.getUserId() + "\nPhone Number : " + o.getPhoneNumber() + "\nEmail : " + o.getEmail() +
                "\n**********************\n" + "Headers :" + payload.headers() + "\nKey : " + payload.key() + "\nPartition : " + payload.partition() + "\n*******************\n"));

    }

   /* @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String , Object> payload) {
        log.info("Tópico: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order: {}", payload.value());
        //System.out.println("******************** " + payload.value());*/
    //}
}