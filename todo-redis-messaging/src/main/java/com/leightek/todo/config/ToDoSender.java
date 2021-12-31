package com.leightek.todo.config;

import com.leightek.todo.domain.ToDo;
import com.leightek.todo.redis.ToDoProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToDoSender {

    @Bean
    CommandLineRunner sendMessage(ToDoProducer producer, @Value("${todo.redis.topic}") String topic) {
        return args -> {
            producer.sendTo(topic, new ToDo("workout tomorrow morning!"));
        };
    }
}
