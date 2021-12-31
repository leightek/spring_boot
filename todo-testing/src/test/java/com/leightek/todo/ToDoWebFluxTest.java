package com.leightek.todo;

import com.leightek.todo.domain.ToDo;
import com.leightek.todo.reactive.ToDoFluxController;
import com.leightek.todo.repository.ToDoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebFluxTest(ToDoFluxController.class)
public class ToDoWebFluxTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private ToDoRepository toDoRepository;

    @Test
    public void testExample() {
        given(this.toDoRepository.findAll()).willReturn(Arrays.asList(new ToDo("Read a Book"),
                new ToDo("Buy Milk")));
        this.webClient.get().uri("/todo-flux").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class);
    }
}
