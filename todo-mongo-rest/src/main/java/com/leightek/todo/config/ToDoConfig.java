package com.leightek.todo.config;

import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

@Configuration
public class ToDoConfig {
    private Environment environment;

    public ToDoConfig(Environment environment) {
        this.environment = environment;
    }

//    @Bean
//    @DependsOn("embeddedMongoServer")
//    public MongoClient mongoClient() {
//        int port = this.environment.getProperty("local.mongo.prot", Integer.class);
//        return new MongoClient("localhost", port);
//    }
}
