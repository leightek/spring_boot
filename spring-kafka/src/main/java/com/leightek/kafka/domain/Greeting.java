package com.leightek.kafka.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class Greeting {

    private String msg;
    private String name;

    public Greeting() {
    }

    public Greeting(String msg, String name) {
        this.msg = msg;
        this.name = name;
    }
}
