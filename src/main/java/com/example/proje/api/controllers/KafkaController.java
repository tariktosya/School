package com.example.proje.api.controllers;

import com.example.proje.model.entity.User;
import com.example.proje.service.UserService;
import com.example.proje.service.kafka.KafkaSenderService;
import com.example.proje.utilities.results.ErrorDataResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(value = "/kafka")
public class KafkaController {

    @Autowired
    private KafkaSenderService kafkaSenderService;

    @Autowired
    private UserService userService;

    @Value("${topic.name.producer}")
    private String topicName;

    @GetMapping(value = "/send")
    public void send() {
        kafkaSenderService.send(userService.getAllUserByDefault());
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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<String, String>();

        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
        return errors;
    }
}
