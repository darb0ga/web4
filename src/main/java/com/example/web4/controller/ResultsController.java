package com.example.web4.controller;

import com.example.web4.dto.ResultDto;
import com.example.web4.entity.Results;
import com.example.web4.jwt.JwtFilter;
import com.example.web4.jwt.JwtUtil;
import com.example.web4.service.ResultService;
import com.example.web4.service.UserService;
import com.example.web4.utils.PasswordHasher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results") // Базовый маршрут для всех операций с пользователями
@CrossOrigin(origins = "*")
public class ResultsController {
    private final ResultService resultService;
    private final UserService userService;

    private final PasswordHasher hasher;

    private final JwtFilter jwt;

    private final JwtUtil util;


    public ResultsController(ResultService resultService, UserService userService, PasswordHasher hasher, JwtFilter jwt, JwtUtil util) {
        this.resultService = resultService;
        this.userService = userService;
        this.hasher = hasher;
        this.jwt = jwt;
        this.util = util;
    }

    @PostMapping(value = "/post")
    private ResponseEntity<Object> addPoint(@RequestBody ResultDto results, HttpServletRequest request) {
        Results newone = new Results(results.getX(), results.getY(), results.getR(), userService.getByLogin("my@test.ru"));
        resultService.addAttemptByCreator(newone, userService.getByLogin("my@test.ru"));
        return new ResponseEntity<>(results, HttpStatus.CREATED);
    }

    @GetMapping(value = "/get")
    private ResponseEntity<List<Results>> getPoints(HttpServletRequest request) {
        System.out.println(resultService.getAllByOwner(23L));

        return ResponseEntity.ok(resultService.getAllByOwner(userService.getByLogin("my@test.ru").getId()));
    }

    @DeleteMapping(value = "delete")
    private ResponseEntity<String> deletePoints(HttpServletRequest request) {
        var user = userService.getByLogin("my@test.ru");
        if (user != null) {
            resultService.deleteByOwner(user.getId());
            return new ResponseEntity<>("Все ваши точки удалены.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Пользователь на аутентифицирован!", HttpStatus.UNAUTHORIZED);
    }


}
