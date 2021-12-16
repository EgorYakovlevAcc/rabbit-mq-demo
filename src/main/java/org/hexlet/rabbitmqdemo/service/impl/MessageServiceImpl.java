package org.hexlet.rabbitmqdemo.service.impl;

import org.hexlet.rabbitmqdemo.dto.MessageDto;
import org.hexlet.rabbitmqdemo.model.Message;
import org.hexlet.rabbitmqdemo.repo.MessageRepo;
import org.hexlet.rabbitmqdemo.service.MessageService;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MessageServiceImpl implements MessageService {
    private MessageRepo messageRepo;

    public MessageServiceImpl(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public void save(MessageDto messageDto) {
        messageRepo.save(toMessage(messageDto));
    }

    @Override
    public List<MessageDto> getAllMessages() {
        List<Message> messages = messageRepo.findAll();
        return CollectionUtils.isEmpty(messages)
                ? List.of()
                : messages.stream()
                .map(this::toMessageDto)
                .collect(Collectors.toList());
    }

    private MessageDto toMessageDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setAuthor(message.getAuthor());
        messageDto.setUserType(message.getUserType());
        messageDto.setText(message.getText());
        return messageDto;
    }

    private Message toMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setAuthor(messageDto.getAuthor());
        message.setCreationDate(new Date());
        message.setText(messageDto.getText());
        message.setUserType(messageDto.getUserType());
        return message;
    }
}
