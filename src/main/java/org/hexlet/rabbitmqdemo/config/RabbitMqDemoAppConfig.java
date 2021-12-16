package org.hexlet.rabbitmqdemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hexlet.rabbitmqdemo.queue.QueueConsumer;
import org.hexlet.rabbitmqdemo.repo.MessageRepo;
import org.hexlet.rabbitmqdemo.service.MessageService;
import org.hexlet.rabbitmqdemo.service.impl.MessageServiceImpl;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqDemoAppConfig {
    @Bean
    public Queue messagesCustomerQueue() {
        return new Queue("messages-customer", false);
    }

    @Bean
    public Queue messagesBackofficeQueue() {
        return new Queue("messages-backoffice", false);
    }

    @Bean
    public QueueConsumer queueConsumer(MessageService messageService, ObjectMapper objectMapper) {
        return new QueueConsumer(messageService, objectMapper);
    }

    @Bean
    public MessageService messageService(MessageRepo messageRepo) {
        return new MessageServiceImpl(messageRepo);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }
}
