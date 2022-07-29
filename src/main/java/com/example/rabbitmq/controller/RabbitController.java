package com.example.rabbitmq.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
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
    public ResponseEntity<String> createUser(@RequestParam("messageData") String messageData){
      Message<String> message = MessageBuilder.withPayload(messageData).setHeader("routing_key","user").build();
        Message<String> message2 = MessageBuilder.withPayload("informacja").setHeader("routing_key","user").build();
streamBridge.send("userExchange-out-0",message);
streamBridge.send("userExchange-out-0",message2);
        return ResponseEntity.ok().body("USER_CREATED");
    }

    @PostMapping("/article")
    public ResponseEntity<String> createArticle(@RequestParam("messageData") String messageData){
        Message<String> message = MessageBuilder.withPayload(messageData).setHeader("routing_key","article").build();
        streamBridge.send("articleExchange-out-0",message);
        return ResponseEntity.ok().body("ARTICLE_CREATED");
    }
    @PutMapping("/articleUpdate")
    public ResponseEntity<String> updateArticle(@RequestParam("messageData") String messageData){
        Message<String> message = MessageBuilder.withPayload(messageData).setHeader("routing_key","article").build();
        streamBridge.send("articleExchange-out-0",message);

    return ResponseEntity.ok().body("USER_UPDATED");
    }
    @PostMapping("/comment")
    public ResponseEntity<String> createComment(@RequestParam("messageData") String messageData){
        Message<String> message = MessageBuilder.withPayload(messageData).setHeader("routing_key","comment").build();;
        streamBridge.send("commentExchange-out-0",message);
    return ResponseEntity.ok().body("COMMENT_CREATED");
    }

    @PutMapping("/commentUpdate")
    public ResponseEntity<String> updateComment(@RequestParam("messageData") String messageData){
        Message<String> message = MessageBuilder.withPayload(messageData).setHeader("routing_key","comment").build();
        streamBridge.send("commentExchange-out-0",message);
    return ResponseEntity.ok().body("COMMENT_UPDATED");
    }

    @DeleteMapping("/commentDeleted")
    public ResponseEntity<String> deleteComment(@RequestParam("messageData") String messageData){
        Message<String> message = MessageBuilder.withPayload(messageData).setHeader("routing_key","comment").build();
        streamBridge.send("commentExchange-out-0",message);
    return ResponseEntity.ok().body("COMMENT_DELETED");
    }

    @PostMapping("/emailToUser")
    public ResponseEntity<String> sendEmailToUser(@RequestParam("messageData") String messageData){
        Message<String> message = MessageBuilder.withPayload(messageData).setHeader("routing_key","email").build();
        streamBridge.send("receiver-out-0",message);
        return ResponseEntity.ok().body("EMAIL_SEND");
    }
}

