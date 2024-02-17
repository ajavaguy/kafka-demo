package com.ajavaguy.kafka.consumer;

import com.ajavaguy.kafka.message.MessageCreated;
import com.ajavaguy.kafka.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private final ProducerService producerService;

    @Autowired
    public MessageConsumer(ProducerService producerService) {
        this.producerService = producerService;
    }

    @KafkaListener(
            id = "messageJSONConsumerClient",
            topics = {"message.topics"},
            groupId = "message.created.consumer",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(@Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(KafkaHeaders.RECEIVED_KEY) String key,
                       @Payload MessageCreated message) {
        try {
            producerService.send(partition, key, message);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
