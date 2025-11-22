package com.awais.todo_mangement.service;

import com.awais.todo_mangement.dto.TodoDto;

import java.util.List;

public interface TodoService {
    TodoDto createTodo(TodoDto todoDto);
    TodoDto getTodoById(Long id);
    List<TodoDto> getAllTodos();
    TodoDto updateTodo(TodoDto todoDto, Long id);
    void deleteTodo(Long id);
    TodoDto completeTodo(Long id);
    TodoDto incompleteTodo(Long id);
}
