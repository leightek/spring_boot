package com.leightek.todo.config;

import com.leightek.todo.domain.ToDo;
import com.leightek.todo.rmq.ToDoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@EnableScheduling
@Configuration
public class ToDoSender {

    @Autowired
    private ToDoProducer producer;

    @Value("${todo.amqp.queue}")
    private String destination;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 500L)
    private void sendToDos() {
        producer.sentTo(destination, new ToDo("Thinking on Spring Boot at " + dateFormat.format(new Date())));
    }
}
