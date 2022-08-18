package com.example.proje.api.controllers;

import com.example.proje.service.kafka.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Value;

import org.apache.kafka.clients.consumer.ConsumerRecord;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final TopicService topicService;


    @GetMapping(value = "/send")
    public void send(){
        topicService.send("Mesajımı almistir oooo Almasa da olur ooo");
        topicService.send("ikinci mesaji atiyorum ona gore");
        topicService.send("ucuncu mesaj da gittiyse dagilalim");
    }






}
