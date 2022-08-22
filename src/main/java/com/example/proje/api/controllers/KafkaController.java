package com.example.proje.api.controllers;

import com.example.proje.model.entity.City;
import com.example.proje.model.entity.User;
import com.example.proje.model.response.user.UserListDto;
import com.example.proje.service.CityService;
import com.example.proje.service.UserService;
import com.example.proje.service.kafka.KafkaSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Autowired
    private UserService userService;

    @Autowired
    private KafkaTemplate<String, List<User>> kafkaTemplate;

    @Value("${topic.name.producer}")
    private String topicName;

    @GetMapping(value = "/send")
    public void send() throws InterruptedException {
        kafkaTemplate.send(topicName, UUID.randomUUID().toString(), userService.getAllUserByDefault());
        // kafkaSenderService.send(cityService.getAllCity());
//        List<String> stringList = new ArrayList<String>();
//        stringList.add("Mesajimi almistir oooo Almasa da olur ooo");
//        stringList.add("ikinci mesaji atiyorum ona gore");
//        stringList.add("ucuncu mesaj da gittiyse dagilalim");
//        topicService.send("Mesajimi almistir oooo Almasa da olur ooo");
//        topicService.send("ikinci mesaji atiyorum ona gore");
//        topicService.send("ucuncu mesaj da gittiyse dagilalim");
//    }
    }
}
