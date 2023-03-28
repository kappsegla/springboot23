package com.example.springbootdemo.service;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Publisher {

    RabbitTemplate template;
    String MESSAGE_QUEUE = "messages";

    public Publisher(RabbitTemplate template) {
        this.template = template;
    }

    public void publishMessage(String message) {
        template.convertAndSend(MESSAGE_QUEUE, message);
    }

    @Bean
    public Queue queue() {
        return new Queue(MESSAGE_QUEUE);
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(MESSAGE_QUEUE);
        container.setMessageListener(message -> System.out.println("Incoming message: " + new String(message.getBody())));
        return container;
    }

}
