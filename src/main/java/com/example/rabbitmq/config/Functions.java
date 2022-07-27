package com.example.rabbitmq.config;

import com.rabbitmq.client.AMQP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration

public class Functions {

Logger logger = LoggerFactory.getLogger(Functions.class);

    @Bean
    Consumer<String> userExchange(){
        return message-> {
            logger.info("Received message from user :" + message);

        };
    }

@Bean
    Consumer<Message<String>> articleExchange() {
    return message -> {
        logger.info("Received message from article:" + message);
    };
}

@Bean
Consumer<Message<String>> commentExchange(){
    return message -> {
        logger.info("Received message from comment:" + message);
    };
}

@Bean
Consumer<Message<String>> receiver(){
    return message->{
        logger.info("Received message from email :" + message);

    };
}

    @Bean
    Consumer<Message<String>> fromAll(){
        return message->{
            logger.info("Received message from all :" + message);

        };
    }







}
