package com.gladkiei.tasktracker.services.event.publisher;

import com.gladkiei.tasktracker.dtos.user.UserResponseDto;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaEventPublisher implements EventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${kafka.topic.email-sending-tasks}")
    private String topic;

    @Value("${kafka.event-type.welcome}")
    private String eventType;

    public KafkaEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

//    @Override
//    public void sendRegistrationEvent(UserResponseDto userResponseDto) {
//        kafkaTemplate.send(
//                topic,
//                MessageBuilder
//                        .withPayload(userResponseDto)
//                        .setHeader("event-type", eventType)
//                        .build());
//
//    }

    @Override
    public void sendRegistrationEvent(UserResponseDto userResponseDto) {
        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, userResponseDto);
        record.headers().add(new RecordHeader("event-type", eventType.getBytes()));
        kafkaTemplate.send(record);
    }
}
