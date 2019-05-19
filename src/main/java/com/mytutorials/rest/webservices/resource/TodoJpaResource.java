package com.mytutorials.rest.webservices.resource;


import com.mytutorials.rest.webservices.Repository.TodoJpaRepository;
import com.mytutorials.rest.webservices.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoJpaResource {

    @Autowired
    private TodoJpaRepository todoJpaRepository;

    @Autowired
    public TodoJpaResource(TodoJpaRepository todoJpaRepository) {
        this.todoJpaRepository = todoJpaRepository;
    }

    @GetMapping("jpa/users/{username}/todos")
    public List<Todo> getAllTodo(@PathVariable String username) {
        return todoJpaRepository.findByUserName(username);
    }

    @GetMapping("jpa/users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable long id) {
        return todoJpaRepository.findById(id).get();
    }

    @DeleteMapping("jpa/users/{username}/todos/{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable String username,
                                           @PathVariable long id) {

        todoJpaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("jpa/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username,
                                           @PathVariable long id,
                                           @RequestBody Todo todo) {

        Todo updatedTodo = todoJpaRepository.save(todo);
        return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);

    }

    @PostMapping("jpa/users/{userName}/todos")
    public ResponseEntity createTodo(@PathVariable String userName,
                                     @RequestBody Todo todo) {

        Todo createdTodo = todoJpaRepository.save(todo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdTodo.getId()).toUri();

        //Location
        return ResponseEntity.created(uri).build();

    }
}
