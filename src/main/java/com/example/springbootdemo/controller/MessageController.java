package com.example.springbootdemo.controller;

import com.example.springbootdemo.service.Publisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final Publisher publisher;

    public MessageController(Publisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping
    public void postMessage(@RequestBody String message){
        publisher.publishMessage(message);
    }
}
