package org.hexlet.rabbitmqdemo.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hexlet.rabbitmqdemo.dto.MessageDto;
import org.hexlet.rabbitmqdemo.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@EnableRabbit
public class QueueConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueConsumer.class);
    private MessageService messageService;
    private ObjectMapper objectMapper;

    public QueueConsumer(MessageService messageService, ObjectMapper objectMapper) {
        this.messageService = messageService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "messages")
    public void processQueue1(String message) throws JsonProcessingException {
        LOGGER.info("Get msg from rabbitMQ: " + message);
        MessageDto messageDto = objectMapper.readValue(message, MessageDto.class);
        messageService.save(messageDto);
    }
}
