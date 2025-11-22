package com.awais.todo_mangement.repository;

import com.awais.todo_mangement.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
