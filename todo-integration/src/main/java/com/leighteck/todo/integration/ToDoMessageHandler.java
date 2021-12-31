package com.leighteck.todo.integration;

import com.leighteck.todo.domain.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ToDoMessageHandler {
    private Logger log = LoggerFactory.getLogger(ToDoMessageHandler.class);

    public void process(ToDo todo) {
        log.info(">>> {}", todo);
        // more process...
    }
}
