package com.awais.todo_mangement.service.impl;

import com.awais.todo_mangement.dto.TodoDto;
import com.awais.todo_mangement.entity.Todo;
import com.awais.todo_mangement.exceptions.ResourceNotFoundException;
import com.awais.todo_mangement.repository.TodoRepository;
import com.awais.todo_mangement.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
     Todo todo = modelMapper.map(todoDto,Todo.class);
     Todo savedTodo = todoRepository.save(todo);
     return modelMapper.map(savedTodo, TodoDto.class);

    }

    @Override
    public TodoDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Todo not found with id " + id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map((todo )-> modelMapper.map(todo,TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Todo not found with id " + id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        Todo savedtodo = todoRepository.save(todo);
        return modelMapper.map(savedtodo,TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Todo not found with id " + id));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Todo not found with id " + id));
      todo.setCompleted(Boolean.TRUE);
      Todo saved = todoRepository.save(todo);
      return modelMapper.map(saved,TodoDto.class);
    }

    @Override
    public TodoDto incompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Todo not found with id " + id));
        todo.setCompleted(Boolean.FALSE);
        Todo saved = todoRepository.save(todo);
        return modelMapper.map(saved,TodoDto.class);
    }


}
