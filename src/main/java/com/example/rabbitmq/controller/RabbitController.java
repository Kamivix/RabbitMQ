package com.example.rabbitmq.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
public class RabbitController {
StreamBridge streamBridge;
@Autowired
    public RabbitController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(){
      Message<String> message = MessageBuilder.withPayload("USER_CREATED").setHeader("routing_key","user").build();
streamBridge.send("userExchange-out-0",message);
        return ResponseEntity.ok().body("USER_CREATED");
    }

    @PostMapping("/article")
    public ResponseEntity<String> createArticle(){
        Message<String> message = MessageBuilder.withPayload("ARTICLE_CREATED").setHeader("routing_key","article").build();
        streamBridge.send("articleExchange-out-0",message);
        return ResponseEntity.ok().body("ARTICLE_CREATED");
    }
    @PutMapping("/articleUpdate")
    public ResponseEntity<String> updateArticle(){
        Message<String> message = MessageBuilder.withPayload("USER_UPDATED").setHeader("routing_key","article").build();
        streamBridge.send("articleExchange-out-0",message);

    return ResponseEntity.ok().body("USER_UPDATED");
    }
    @PostMapping("/comment")
    public ResponseEntity<String> createComment(){
        Message<String> message = MessageBuilder.withPayload("COMMENT_CREATED").setHeader("routing_key","comment").build();;
        streamBridge.send("commentExchange-out-0",message);
    return ResponseEntity.ok().body("COMMENT_CREATED");
    }

    @PutMapping("/commentUpdate")
    public ResponseEntity<String> updateComment(){
        Message<String> message = MessageBuilder.withPayload("COMMENT_UPDATED").setHeader("routing_key","comment").build();
        streamBridge.send("commentExchange-out-0",message);
    return ResponseEntity.ok().body("COMMENT_UPDATED");
    }

    @DeleteMapping("/commentDeleted")
    public ResponseEntity<String> deleteComment(){
        Message<String> message = MessageBuilder.withPayload("COMMENT_DELETED").setHeader("routing_key","comment").build();
        streamBridge.send("commentExchange-out-0",message);
    return ResponseEntity.ok().body("COMMENT_DELETED");
    }

    @PostMapping("/emailToUser")
    public ResponseEntity<String> sendEmailToUser(){
        Message<String> message = MessageBuilder.withPayload("EMAIL_SEND").setHeader("routing_key","email").build();
        streamBridge.send("receiver-out-0",message);
        return ResponseEntity.ok().body("EMAIL_SEND");
    }
}

