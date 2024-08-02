package com.asemokamichi.kz.edupath.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CourseProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CourseProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message){
        kafkaTemplate.send("course", message);
    }
}
