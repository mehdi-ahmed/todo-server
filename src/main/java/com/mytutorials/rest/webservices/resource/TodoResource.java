package com.mytutorials.rest.webservices.resource;

import com.mytutorials.rest.webservices.entity.Todo;
import com.mytutorials.rest.webservices.todo.TodoHardcodedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoResource {

    private final TodoHardcodedService todoHardcodedService;

    @Autowired
    public TodoResource(TodoHardcodedService todoHardcodedService) {
        this.todoHardcodedService = todoHardcodedService;
    }

    @GetMapping("users/{username}/todos")
    public List<Todo> getAllTodo(@PathVariable String username) {
        return todoHardcodedService.findAll();
    }

    @GetMapping("users/{username}/todos/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable String username, @PathVariable long id) {

        Todo todo = todoHardcodedService.findById(id);
        if (todo == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @DeleteMapping("users/{username}/todos/{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable String username,
                                           @PathVariable long id) {

        Todo todo = todoHardcodedService.deleteById(id);
        if (todo != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

    @PutMapping("users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username,
                                           @PathVariable long id,
                                           @RequestBody Todo todo) {

        Todo todoUpdated = todoHardcodedService.save(todo);
        return new ResponseEntity<Todo>(todoUpdated, HttpStatus.OK);

    }

    @PostMapping("users/{username}/todos")
    public ResponseEntity createTodo(@PathVariable String username,
                                     @RequestBody Todo todo) {

        Todo createdTodo = todoHardcodedService.save(todo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdTodo.getId()).toUri();

        //Location
        return ResponseEntity.created(uri).build();

    }
}
