package org.hexlet.rabbitmqdemo.service;

import org.hexlet.rabbitmqdemo.dto.MessageDto;
import org.hexlet.rabbitmqdemo.model.Message;

import java.util.List;

public interface MessageService {
    void save(MessageDto messageDto);

    List<MessageDto> getAllMessages();
}
