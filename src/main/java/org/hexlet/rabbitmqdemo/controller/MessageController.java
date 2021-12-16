package org.hexlet.rabbitmqdemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hexlet.rabbitmqdemo.dto.MessageDto;
import org.hexlet.rabbitmqdemo.model.Message;
import org.hexlet.rabbitmqdemo.service.MessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0/messages/")
public class MessageController {
    private ObjectMapper objectMapper;
    private RabbitTemplate rabbitTemplate;
    private MessageService messageService;

    @Autowired
    public MessageController(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate, MessageService messageService) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
        this.messageService = messageService;
    }

    @PostMapping("/")
    public String sendMessage(@RequestBody MessageDto messageDto) throws JsonProcessingException {
        rabbitTemplate.convertAndSend("messages", objectMapper.writeValueAsString(messageDto));
        return "Message has been successfully send";
    }

    @GetMapping("/")
    public List<MessageDto> getAllMessages() {
        return messageService.getAllMessages();
    }
}
