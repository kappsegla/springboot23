package com.example.springbootdemo.service;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Publisher {

    RabbitTemplate template;
    String MESSAGE_QUEUE = "messages";
    String EMAIL_QUEUE = "emails";

    public Publisher(RabbitTemplate template) {
        this.template = template;
    }

//    public void publishMessage(String message) {
//        template.convertAndSend(MESSAGE_QUEUE, message);
//    }
    public void publishMessage(String message) {
        template.convertAndSend("my.topic","message.data", message);
    }

    @Bean
    public Queue messageQueue() {
        return new Queue(MESSAGE_QUEUE);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("my.topic");
    }

    @Bean
    public Binding messages(Queue messageQueue, TopicExchange exchange){
        return BindingBuilder.bind(messageQueue).to(exchange).with("message.*");
    }

    @Bean
    public Binding emails(Queue emailQueue, TopicExchange exchange){
        return BindingBuilder.bind(emailQueue).to(exchange).with("message.email");
    }


    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(MESSAGE_QUEUE);
        container.setMessageListener(message -> {
            System.out.println("Message: " + new String(message.getBody()));
        });
        return container;
    }

}
