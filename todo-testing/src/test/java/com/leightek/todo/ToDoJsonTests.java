package com.leightek.todo;

import com.leightek.todo.domain.ToDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class ToDoJsonTests {

    @Autowired
    private JacksonTester<ToDo> json; // ignore Intellij inspection warning - Can not autowire. No beans of "JacksonTester" found

    @Test
    public void toDoSerializeTest() throws IOException {
        ToDo toDo = new ToDo("Read a Book");

        assertThat(this.json.write(toDo)).isEqualToJson("todo.json");

        assertThat(this.json.write(toDo)).hasJsonPathStringValue("@.description");
        assertThat(this.json.write(toDo)).extractingJsonPathStringValue("@.description")
                .isEqualTo("Read a Book");
    }

    @Test
    public void toDoDesericalizeTest() throws IOException {
        String content ="{\"description\":\"Read a Book\", \"completed\": true}";
        assertThat(this.json.parse(content)).isEqualTo(new ToDo("Read a Book", true));
        assertThat(this.json.parseObject(content).getDescription()).isEqualTo("Read a Book");
    }
}
