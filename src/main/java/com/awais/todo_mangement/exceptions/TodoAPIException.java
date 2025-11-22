package com.awais.todo_mangement.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Data
public class TodoAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;

}
