package com.example.web4.controller;

import com.example.web4.dto.UserDto;
import com.example.web4.entity.User;
import com.example.web4.jwt.JwtUtil;
import com.example.web4.service.UserService;
import com.example.web4.utils.PasswordHasher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") // Базовый маршрут для всех операций с пользователями
@CrossOrigin(origins = "*")
public class UserController {
    private final PasswordHasher passwordHasher;

    private final UserService userService;


    private final AuthenticationManager authManager = authentication -> null;

    public UserController(UserService userService, PasswordHasher hasher) {
        this.userService = userService;
        this.passwordHasher = hasher;
    }


    // Получение пользователя по ID
    @PostMapping(value = "/login")
    public ResponseEntity<String> getUserById(@RequestBody UserDto userDto) {
        var name = userDto.getLogin();
        var password = passwordHasher.hashPassword(userDto.getPassword());
        var user = userService.getByLoginAndPassword(name, password);

        if (user != null) {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
            JwtUtil jwt = new JwtUtil();
            String token = jwt.generateToken(name);
            return ResponseEntity.ok(token);
        }

        return new ResponseEntity<>("No such email and password", HttpStatus.NOT_FOUND);

    }


    // Создание нового пользователя
    @PostMapping(value = "/register")
    public ResponseEntity<String> createUser(@RequestBody User userDto) {
        String login = userDto.getLogin();
        String password = passwordHasher.hashPassword(userDto.getPassword());
        var user = userService.getByLogin(login);

        if (user != null) {
            return new ResponseEntity<>("User with the same email exists", HttpStatus.BAD_REQUEST);
        }
        if (userService.register(login, password)) {
            return new ResponseEntity<>("User has been created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);


}


}
