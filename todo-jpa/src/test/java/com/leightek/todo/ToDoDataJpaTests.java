package com.leightek.todo;

import com.leightek.todo.domain.ToDo;
import com.leightek.todo.repository.ToDoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // required for real database
public class ToDoDataJpaTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ToDoRepository repository;

    @Test
    public void toDoDataTest() {
        this.entityManager.persist(new ToDo("Read a Book"));
        Iterable<ToDo> toDos = this.repository.findByDescriptionContains("Read a Book");
        assertThat(toDos.iterator().next()).toString().contains("Read a Book");
    }
}
