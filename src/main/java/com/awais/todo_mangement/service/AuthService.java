package com.awais.todo_mangement.service;

import com.awais.todo_mangement.dto.LoginDto;
import com.awais.todo_mangement.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
