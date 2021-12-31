package com.leightek.todo;

import com.leightek.todo.domain.ToDo;
import com.leightek.todo.repository.CommonRepository;
import com.leightek.todo.repository.ToDoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JdbcTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ToDoJdbcTests {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private CommonRepository<ToDo> repository;

    @Test
    public void toDoJdbcTest() {
        ToDo toDo = new ToDo("Read a Book");

        this.repository = new ToDoRepository(jdbcTemplate);
        this.repository.save(toDo);

        ToDo result = this.repository.findById(toDo.getId());
        assertThat(result.getId()).isEqualTo(toDo.getId());
    }
}
