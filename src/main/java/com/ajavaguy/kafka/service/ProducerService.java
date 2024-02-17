package com.ajavaguy.kafka.service;

import com.ajavaguy.kafka.message.MessageCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public ProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Integer partition, String key, MessageCreated messageCreated) throws Exception {
        messageCreated.setContent("some content!");
        kafkaTemplate.send("message.topics", key, messageCreated).get();
        System.out.println("Sent messageCreated: " + messageCreated + " partition: " + partition + " key: " + key);
    }
}
