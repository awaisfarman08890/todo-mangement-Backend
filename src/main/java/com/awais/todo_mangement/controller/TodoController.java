package com.awais.todo_mangement.controller;

import com.awais.todo_mangement.dto.TodoDto;
import com.awais.todo_mangement.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@Data
@AllArgsConstructor
public class TodoController {
    private final TodoService todoService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto) {
        TodoDto todo = todoService.createTodo(todoDto);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable Long id){
        TodoDto todoDto = todoService.getTodoById(id);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        List<TodoDto> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable Long id){
        TodoDto todo = todoService.updateTodo(todoDto, id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDto> completetodo(@PathVariable Long id){
        TodoDto todo = todoService.completeTodo(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/{id}/incomplete")
    public ResponseEntity<TodoDto> incompletetodo(@PathVariable Long id){
        TodoDto todo = todoService.incompleteTodo(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

}
