package com.asemokamichi.kz.edupath.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CourseConsumer {

    @KafkaListener(topics = "course", groupId = "my-group")
    public void listen(String message){
        System.out.println("Recieved message = " + message);
    }
}
